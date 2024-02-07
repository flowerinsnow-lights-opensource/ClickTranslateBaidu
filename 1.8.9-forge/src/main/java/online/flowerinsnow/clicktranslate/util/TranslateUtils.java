package online.flowerinsnow.clicktranslate.util;

import com.google.gson.Gson;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.clicktranslate.config.Config;
import online.flowerinsnow.clicktranslate.exception.TranslateException;
import online.flowerinsnow.clicktranslate.object.TranslateLanguage;
import online.flowerinsnow.clicktranslate.object.TranslateResult;
import online.flowerinsnow.clicktranslate.object.json.TranslateResponse;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@SideOnly(Side.CLIENT)
public abstract class TranslateUtils {
    private TranslateUtils() {
    }

    private static final char[] az09 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final SecureRandom RANDOM = new SecureRandom();

    public static TranslateResult translate(String message) {
        TranslateLanguage from = TranslateLanguage.AUTO;
        TranslateLanguage to = TranslateLanguage.getByName(Config.propertyToLanguage.getString());
        if (to == null) {
            throw new TranslateException("click-translate-baidu.command.error.nosuchtolanguage");
        }
        String appid = Config.propertyAppID.getString();
        char[] salt = new char[8];
        for (int i = 0; i < salt.length; i++) {
            salt[i] = az09[RANDOM.nextInt(az09.length)];
        }
        HttpsURLConnection conn = null;
        InputStream in = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            URL url = new URL(Config.propertyAPI.getString() + "?q=" + URLEncoder.encode(message, "UTF-8") + "&from=" + from.name + "&to=" + to.name + "&appid=" + appid + "&salt=" + new String(salt) + "&sign=" + md5(appid, message, new String(salt), Config.propertyAppSecret.getString()));
            conn = (HttpsURLConnection) url.openConnection();
            in = conn.getInputStream();
            br = new BufferedReader((isr = new InputStreamReader(in, StandardCharsets.UTF_8)));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            TranslateResponse response =  new Gson().fromJson(sb.toString(), TranslateResponse.class);
            if (response.getErrorCode() != null) {
                throw new TranslateException(response.getErrorCode() + "(" + response.getErrorMsg() + ")");
            }
            return new TranslateResult(TranslateLanguage.getByName(response.getFrom()), TranslateLanguage.getByName(response.getTo()), response.getTransResult().get(0).getSrc(), response.getTransResult().get(0).getDst());
        } catch (IOException e) {
            throw new TranslateException(e.toString());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ignored) {
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ignored) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static String md5(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string);
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(sb.toString().getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            byte left = (byte) ((b >>> 4) & 0xF);
            byte right = (byte) (b & 0xF);
            sb.append(HEX[left]);
            sb.append(HEX[right]);
        }
        return sb.toString();
    }
}

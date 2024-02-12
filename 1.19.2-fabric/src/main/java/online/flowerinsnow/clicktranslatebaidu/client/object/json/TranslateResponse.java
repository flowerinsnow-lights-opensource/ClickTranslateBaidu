package online.flowerinsnow.clicktranslatebaidu.client.object.json;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import online.flowerinsnow.clicktranslatebaidu.client.object.NoObfuscation;

import java.util.List;
import java.util.Objects;

@NoObfuscation
@Environment(EnvType.CLIENT)
public class TranslateResponse {
    private String from;
    private String to;
    private List<TransResult> trans_result;
    private String error_code;
    private String error_msg;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslateResponse that = (TranslateResponse) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(trans_result, that.trans_result) && Objects.equals(error_code, that.error_code) && Objects.equals(error_msg, that.error_msg);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (trans_result != null ? trans_result.hashCode() : 0);
        result = 31 * result + (error_code != null ? error_code.hashCode() : 0);
        result = 31 * result + (error_msg != null ? error_msg.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TranslateResponse{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", trans_result=" + trans_result +
                ", error_code='" + error_code + '\'' +
                ", error_msg='" + error_msg + '\'' +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public List<TransResult> getTransResult() {
        return trans_result;
    }

    public String getErrorCode() {
        return error_code;
    }

    public String getErrorMsg() {
        return error_msg;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setTransResult(List<TransResult> trans_result) {
        this.trans_result = trans_result;
    }

    public void setErrorCode(String error_code) {
        this.error_code = error_code;
    }

    public void setErrorMsg(String error_msg) {
        this.error_msg = error_msg;
    }

    @NoObfuscation
    public static class TransResult {
        private String src;
        private String dst;

        public String getSrc() {
            return src;
        }

        public String getDst() {
            return dst;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TransResult that = (TransResult) o;
            return Objects.equals(src, that.src) && Objects.equals(dst, that.dst);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + (src != null ? src.hashCode() : 0);
            result = 31 * result + (dst != null ? dst.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "TransResult{" +
                    "src='" + src + '\'' +
                    ", dst='" + dst + '\'' +
                    '}';
        }
    }
}

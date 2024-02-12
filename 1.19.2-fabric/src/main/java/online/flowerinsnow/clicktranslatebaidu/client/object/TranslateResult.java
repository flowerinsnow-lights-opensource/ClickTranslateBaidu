package online.flowerinsnow.clicktranslatebaidu.client.object;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class TranslateResult {
    private TranslateLanguage from;
    private TranslateLanguage to;
    private String src;
    private String dst;

    public TranslateResult(TranslateLanguage from, TranslateLanguage to, String src, String dst) {
        this.from = from;
        this.to = to;
        this.src = src;
        this.dst = dst;
    }

    public TranslateLanguage getFrom() {
        return from;
    }

    public TranslateLanguage getTo() {
        return to;
    }

    public String getSrc() {
        return src;
    }

    public String getDst() {
        return dst;
    }

    public void setFrom(TranslateLanguage from) {
        this.from = from;
    }

    public void setTo(TranslateLanguage to) {
        this.to = to;
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
        TranslateResult that = (TranslateResult) o;
        return from == that.from && to == that.to && Objects.equals(src, that.src) && Objects.equals(dst, that.dst);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (src != null ? src.hashCode() : 0);
        result = 31 * result + (dst != null ? dst.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TranslateResult{" +
                "from=" + from.name +
                ", to=" + to.name +
                ", src='" + src + '\'' +
                ", dst='" + dst + '\'' +
                '}';
    }
}

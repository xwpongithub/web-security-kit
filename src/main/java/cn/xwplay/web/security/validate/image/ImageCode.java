package cn.xwplay.web.security.validate.image;

import cn.xwplay.web.security.validate.ValidateCode;
import lombok.*;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * 图片验证码
 * @author 肖文彭
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn){
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, Date expireTime){
        super(code, expireTime);
        this.image = image;
    }

}

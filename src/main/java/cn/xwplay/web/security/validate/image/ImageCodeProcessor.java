package cn.xwplay.web.security.validate.image;

import cn.xwplay.web.security.validate.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    public void send(HttpServletRequest request,HttpServletResponse response, ImageCode imageCode) {
        try {
            ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

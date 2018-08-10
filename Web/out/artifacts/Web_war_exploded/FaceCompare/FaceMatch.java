package FaceCompare;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class FaceMatch {

    public static String match() {
        // 请求url

        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            String image1 ="http://choresefree.com:666/photo/11.jpg";
            String image2 ="http://choresefree.com:666/photo/22.jpg";
            List<Map<String, Object>> images = new ArrayList<>();
            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "URL");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NONE");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "URL");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "NONE");

            images.add(map1);
            images.add(map2);

            String param = GsonUtils.toJson(images);
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.c784a6bab0abcb90e5402457b1fc69e9.2592000.1536464547.282335-11657487";
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println("结果是"+result);
            System.out.println(result.substring(113, 118));
            //上两句获取的是要取出字符串的前后坐标
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        FaceMatch.match();
    }
}
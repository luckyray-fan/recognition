import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@WebServlet(name = "getToken",urlPatterns = "/getToken")
public class getToken extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accesss_url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials"+"&client_id="+APIContants.API_KEY
                +"&client_secret="+ APIContants.SERCET_KEY;
        try{
            URL realUrl = new URL(accesss_url);
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();

            for(String key : map.keySet())
            {
                System.err.println(key+"--->"+map.get(key));
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while((line = in.readLine())!=null)
            {
                result+=line;
            }
            System.err.println("result:"+result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            PrintWriter out = response.getWriter();
            out.println(access_token);
        }catch (Exception e)
        {
            System.err.printf("获取token失败！");
        }
        APIContants.ACCESS_TOKEN = accesss_url;
    }

}
class APIContants {
    static final String API_KEY = "VBZGRLekvfgeyLzay0qYFRLN";
    static final String SERCET_KEY = "qoicdP5Bqg0sOiFAoPnGyAi5gfTAlsba";
    static String ACCESS_TOKEN = "24.6067fb11f536737f43fa8453bf162065.2592000.1536469730.282335-11658027";
}

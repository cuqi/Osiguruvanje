import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.security.KeyStore;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.security.cert.X509Certificate;
import java.security.SecureRandom;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

public class TestClient {
    private static final String endpoint = "https://localhost:8080/";
    private static final String truststore = "src\\main\\java\\test.keystore";

    public static void main(String[] args) {
        new TestClient().runTests();
    }
    private void runTests() {
        try {
            SSLContext sslCtx = SSLContext.getInstance("TLS");
            char[ ] password = "kakoebrat?".toCharArray();
            KeyStore ks = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream(truststore);
            ks.load(fis, password);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks); // same as keystore
            sslCtx.init(null,                   // not needed, not challenged
                tmf.getTrustManagers(), 
                new SecureRandom());                
    
            HttpsURLConnection.setDefaultSSLSocketFactory(sslCtx.getSocketFactory());

            getTestOne();
            // Proof of concept tests.
            // getTest();
            // postTest();
            // getTestAll();     // confirm POST test
            // deleteTest("31"); 
            // getTestAll();     // confirm DELETE test
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private HttpsURLConnection getConnection(URL url, String verb) {
        try {	
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod(verb);
    
            // Guard against "bad hostname" errors during handshake.
            conn.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String host, SSLSession session) {
                return host.equals("localhost"); // for development
                }
            });
            return conn;
        }
        catch(Exception e) { throw new RuntimeException(e); }
        }

    private void readResponse(String msg, HttpsURLConnection conn) {
        try {
            byte[ ] buffer = new byte[4096]; 
            InputStream in = conn.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
    
            int n = 0;
            // Append chunks to ByteArrayOutputStream.
            while ((n = in.read(buffer)) != -1) out.write(buffer, 0, n); 
            in.close();
    
            System.out.println(new String(out.toByteArray())); // stringify and print
        }
        catch(Exception e) { throw new RuntimeException(e); }
        }

        private void getTestOne() {
            try {
                URL url = new URL(endpoint + "thisyear");
                System.out.println(url.toString());
                HttpsURLConnection conn = getConnection(url, "GET");
                conn.connect();
                readResponse("GET request:\n", conn);
                conn.disconnect();
            }
            catch(Exception e) { throw new RuntimeException(e); }
            }
}

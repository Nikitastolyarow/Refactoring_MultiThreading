package homework;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


import java.util.stream.Collectors;
import org.apache.http.NameValuePair;

public class Request {
    private final String method;
    private final String path;
    private final Map<String, String> headers;
    private final InputStream body;
    private List<NameValuePair> queryParams;

    public Request(String method, String path, Map<String, String> headers, InputStream body) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }


    public void setQueryParams(List<NameValuePair> params) {
        this.queryParams = params;
    }
    public List<NameValuePair> getQueryParams() {
        return queryParams;
    }
    
    public String getQueryParam(String param) {
        return queryParams.stream()
                .filter(p -> p.getName().equals(param))
                .map(p -> p.getValue())
                .collect(Collectors.joining(", "));
    }
    @Override
    public String toString() {
        return "Request{" +
                "\n method='" + method + '\'' +
                "\n path='" + path + '\'' +
                "\n headers=" + headers +
                "\n body='" + body + '\'' +
                "\n queryParams=" + queryParams +
                '}';
    }
}

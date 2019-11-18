
import java.util.stream.Stream;

public class Tool {
    public static void build(String url, String... modules) {
        Stream.of(modules).forEach((module) -> {
            System.out.println("location ^~ /nccloud/" + module + " {");
            System.out.println("\t proxy_pass " + url);
            System.out.println("\t proxy_buffer_size 64k");
            System.out.println("\t proxy_buffers 4 32k");
            System.out.println("\t proxy_busy_buffers_size 64k");
            System.out.println("}");
        });
    }

    public static void build(String url, String moduleStr) {
        String[] modules = moduleStr.split(",");
        build(url, modules);
    }

    public static void main(String[] args) {
        String moduleStr = "uapmw,uapfw,uapec,uapsc,uapbs,baseapp,uapbd,bcbd,iaudit,pubapp,pubapputil,riaaam,riaam," +
                "riacc,riadc,riamm,riaorg,riart\r\n"
                + "		,riasm,riawf,lappreportrt,platform,graphic_report";
        String url = "http://10.16.2.231:8080";
        build(url, moduleStr);
    }

}
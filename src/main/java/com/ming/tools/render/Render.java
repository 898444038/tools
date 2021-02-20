package com.ming.tools.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class Render {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(){{
            //add("select * from user where user_id = #{id} and username = #{username}");
            //add("select * from user where @if(id){user_id = #{id}} and username = #{username}");
            //add("select @for(item in list){item} from user @where(){ @if(id){user_id = #{id}} } and username = #{username}");

            add("delete from user where id = #{id}");
            add("delete from user where @if(id!=null){id = #{id}}");
            //add("delete from user @where(){ id = #{id}}");
        }};
        Map<String,Object> data = new HashMap<>();
        data.put("id",1);
        data.put("username","zhangsan");
        data.put("list",new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
        }});
        for (String str : list){
            RenderResult result = Render.render(str,data);
            System.out.println("原始："+result.getOriginal());
            System.out.println("渲染："+result.getRender());
            System.out.println("------------------------------");
        }
    }

    public static RenderResult render(String str, Map<String,Object> data){
        RenderResult result = new RenderResult();
        result.setOriginal(str);
        result.setOriginalData(data);
        replaceParams(result);
        renderSqlMethods(result);
        renderSqlParams(result);
        return result;
    }

    public static List<RenderParams> replaceParams(RenderResult result){
        Map<String,Object> data = result.getOriginalData();
        String originalStr = result.getOriginal();
        String renderStr = result.getOriginal();
        Pattern p = Pattern.compile("\\#\\{(.*?)}");
        Matcher matcher = p.matcher(originalStr);
        List<RenderParams> paramsList = new ArrayList<>();
        while (matcher.find()) {
            String originalTag = matcher.group();//#{id}
            String field = originalTag.substring(2,originalTag.length()-1);//id
            String renderTag = "#"+field+"#";
            renderStr = renderStr.replace(originalTag,renderTag);
            RenderParams renderParams = new RenderParams();
            renderParams.setOriginalTag(originalTag);
            renderParams.setRenderTag(renderTag);
            renderParams.setField(field);
            renderParams.setValue(data.get(field));
            paramsList.add(renderParams);
        }
        result.setRender(renderStr);
        result.setParamsList(paramsList);
        return paramsList;
    }

    /**
     * @for、@if\@elseif\@else、@while
     * @param result
     * @return
     */
    public static RenderResult renderSqlMethods(RenderResult result){
        Map<String,Object> data = result.getOriginalData();
        String originalStr = result.getRender();
        String renderStr = result.getRender();
        Pattern p = Pattern.compile("\\@if(.*?)} ");
        Matcher matcher = p.matcher(originalStr);
        while (matcher.find()) {
            String str = matcher.group();
        }
        result.setRender(renderStr);
        return result;
    }


    public static RenderResult renderSqlParams(RenderResult result){
        String originalStr = result.getRender();
        String renderStr = result.getRender();
        Pattern p = Pattern.compile("\\#(.*?)\\#");
        Matcher matcher = p.matcher(originalStr);
        while (matcher.find()) {
            String str = matcher.group();
            for (RenderParams params : result.getParamsList()){
                if(str.equals(params.getRenderTag())){
                    if(params.getValue() != null){
                        renderStr = renderStr.replace(str,params.getValue().toString());
                    }
                }
            }
        }
        result.setRender(renderStr);
        return result;
    }
}

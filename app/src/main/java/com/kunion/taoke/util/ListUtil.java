package com.kunion.taoke.util;


import com.kunion.taoke.model.remote.rest.info.ItemInfo;

import java.util.Collection;
import java.util.List;

/**
 * Created by ker on 2016/7/6.
 */
public class ListUtil {

    public static boolean isBlank(Collection<? extends Object> list) {
        if(list == null || list.size() == 0) {
            return true;
        }
        return false;
    }


    public static boolean isNotBlank(Collection<? extends Object> list) {
        if(list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    public static String join(List<String> list, String separator) {
        if(list.size() == 0) return "";
        if(list.size() == 1) return list.get(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }


    public static void addNotRepeat(List<ItemInfo> list, ItemInfo addObject) {
        if(addObject == null)
            return ;

        for (ItemInfo object : list) {
            if(object.getObjectId().equalsIgnoreCase(addObject.getObjectId())) {
                return ;
            }
        }
        list.add(addObject);
    }


    public static boolean isContain(List<ItemInfo> list, ItemInfo addObject) {
        if(addObject == null)
            return false;

        for (ItemInfo object : list) {
            if(object.getObjectId().equalsIgnoreCase(addObject.getObjectId())) {
                return true;
            }
        }
        return false;
    }

    public static void remove(List<ItemInfo> list, ItemInfo addObject) {
        if(addObject == null)
            return ;

        for (ItemInfo object : list) {
            if(object.getObjectId().equalsIgnoreCase(addObject.getObjectId())) {
                list.remove(object);
                return ;
            }
        }
    }
}

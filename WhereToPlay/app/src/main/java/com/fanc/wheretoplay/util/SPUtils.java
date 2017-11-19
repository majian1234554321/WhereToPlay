package com.fanc.wheretoplay.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.fanc.wheretoplay.datamodel.NewUser;
import com.fanc.wheretoplay.datamodel.User;


/**
 * @author cxw
 * @time 2016/3/18 0018 15:02
 * @des 保存一些公有的属性到sp
 * @updateAuthor tylz
 * @updateDate 2016/3/18 0018
 * @updateDes
 */
public class SPUtils {

    private SharedPreferences mSp;
    private Editor mEditor;

    public SPUtils(Context context) {
        mSp = context.getSharedPreferences("fanc", context.MODE_PRIVATE);
        mEditor = mSp.edit();
    }

    /**
     * 保存boolean型变量
     */
    public void putBoolean(String key, boolean value) {
        if (key != null) {
            mEditor.putBoolean(key, value);
            mEditor.commit();
        }
    }

    /**
     * 保存String型变量
     */
    public void putString(String key, String value) {
        if (key != null) {
            mEditor.putString(key, value);
            mEditor.commit();
        }
    }

    /**
     * 保存int型变量
     */
    public void putInt(String key, int value) {
        if (key != null) {
            mEditor.putInt(key, value);
            mEditor.commit();
        }
    }

    /**
     * 保存float型变量
     */
    public void putFloat(String key, float value) {
        if (key != null) {
            mEditor.putFloat(key, value);
            mEditor.commit();
        }
    }

    /**
     * 得到int值
     */
    public int getInt(String key, int defValue) {
        return mSp.getInt(key, defValue);
    }

    /**
     * 得到float值
     */
    public float getFloat(String key, int defValue) {
        return mSp.getFloat(key, defValue);
    }

    /**
     * 得到boolean值
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mSp.getBoolean(key, defValue);
    }

    /**
     * 得到String值
     */
    public String getString(String key, String defValue) {
        return mSp.getString(key, defValue);
    }

    /**
     * 删除Key值,返回boolean是否执行成功！
     */
    public boolean removeKey(String key) {
        return mSp.edit()
                .remove(key)
                .commit();
    }

    /**
     * 删除全部Key值,返回boolean是否执行成功！
     */
    public boolean clear() {
        return mSp.edit()
                .clear()
                .commit();
    }

 /*   public void putFence(Location location){
        putString("user_fen_lat",location.lat);
        putString("user_fen_lng",location.lng);

    }

    public Location getFence(){
        Location loca= new Location();
        loca.lat = getString("user_fen_lat", "");
        loca.lng = getString("user_fen_lng", "");
        return loca;
    }

    public void removeFence(){
        removeKey("user_fen_lat");
        removeKey("user_fen_lng");
    }*/

    public interface UserKey {
        String ID = "user_id";//用户ID
        String PID = "user_pic";// 上级id
        String MOBILE = "user_mobile";// 电话
        String CREATED_TIME = "user_created_time";// 注册时间
        String STATUS = "user_status";//用户状态，枚举值，0-禁用|1-正常，这里只会是1，为0时接口会产生50001错误
        String SHARE_CODE = "user_share_code";// 分享吗
        String TOKEN = "user_token";//令牌，请求其它接口时需要
        String AVATAR = "user_avatar";//头像
        String NICKNAME = "user_nickname";// 昵称
        String GENDER = "user_gender";// 性别
        String BIRTHDAY = "user_birthday";// 生日
        String REGISTERED = "user_registered";// 所在地,
        String SIGNATURE = "user_signature";// 签名
    }

    public void putUser(User user) {
        putString(UserKey.ID, user.getId());
        putString(UserKey.PID, user.getPid());
        putString(UserKey.NICKNAME, user.getNickname());
        putString(UserKey.MOBILE, user.getMobile());
        putString(UserKey.CREATED_TIME, user.getCreated_time());
        putString(UserKey.SHARE_CODE, user.getShare_code());
        putString(UserKey.STATUS, user.getCreated_time());
        putString(UserKey.TOKEN, user.getToken());
        Log.e("token","user设置token :\t"+user.getToken());
        putString(UserKey.AVATAR, user.getAvatar());
        putString(UserKey.GENDER, user.getGender());
        putString(UserKey.BIRTHDAY, user.getBirthday());
        putString(UserKey.REGISTERED, user.getRegistered());
        putString(UserKey.SIGNATURE, user.getSignature());
    }

    public User getUser() {
        User user = new User();
        user.setId(getString(UserKey.ID, ""));
        user.setPid(getString(UserKey.PID, ""));
        user.setNickname(getString(UserKey.NICKNAME, ""));
        user.setMobile(getString(UserKey.MOBILE, ""));
        user.setCreated_time(getString(UserKey.CREATED_TIME, ""));
        user.setShare_code(getString(UserKey.SHARE_CODE, ""));
        user.setStatus(getString(UserKey.STATUS, ""));
        user.setToken(getString(UserKey.TOKEN, ""));
        Log.e("token","gettoken :\t" + getString(UserKey.TOKEN, ""));
        user.setAvatar(getString(UserKey.AVATAR, ""));
        user.setGender(getString(UserKey.GENDER, ""));
        user.setBirthday(getString(UserKey.BIRTHDAY, ""));
        user.setRegistered(getString(UserKey.REGISTERED, ""));
        user.setSignature(getString(UserKey.SIGNATURE, ""));
        return user;
    }

    public void putUser(NewUser.User newUser) {
        putString(UserKey.ID, newUser.getId());
        putString(UserKey.PID, newUser.getPid());
        putString(UserKey.NICKNAME, newUser.getNickname());
        putString(UserKey.MOBILE, newUser.getMobile());
        putString(UserKey.CREATED_TIME, newUser.getCreated_time());
        putString(UserKey.SHARE_CODE, newUser.getShare_code());
        putString(UserKey.STATUS, newUser.getCreated_time());
        putString(UserKey.TOKEN, newUser.getToken());
        Log.e("token","newUser设置token :\t"+newUser.getToken());
        putString(UserKey.AVATAR, newUser.getAvatar());
        putString(UserKey.GENDER, newUser.getGender());
        putString(UserKey.BIRTHDAY, newUser.getBirthday());
        putString(UserKey.REGISTERED, newUser.getRegistered());
        putString(UserKey.SIGNATURE, newUser.getSignature());
    }

    public void removeUser() {
        removeKey(UserKey.ID);
        removeKey(UserKey.PID);
        removeKey(UserKey.NICKNAME);
        removeKey(UserKey.MOBILE);
        removeKey(UserKey.CREATED_TIME);
        removeKey(UserKey.SHARE_CODE);
        removeKey(UserKey.STATUS);
        removeKey(UserKey.TOKEN);
        removeKey(UserKey.AVATAR);
        removeKey(UserKey.GENDER);
        removeKey(UserKey.BIRTHDAY);
        removeKey(UserKey.REGISTERED);
        removeKey(UserKey.SIGNATURE);
    }

}

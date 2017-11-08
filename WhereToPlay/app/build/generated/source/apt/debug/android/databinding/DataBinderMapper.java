
package android.databinding;
import com.fanc.wheretoplay.BR;
class DataBinderMapper  {
    final static int TARGET_MIN_SDK = 17;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.fanc.wheretoplay.R.layout.pop_dialog_sheared:
                    return com.fanc.wheretoplay.databinding.PopDialogShearedBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_register:
                    return com.fanc.wheretoplay.databinding.FragmentRegisterBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_date:
                    return com.fanc.wheretoplay.databinding.ItemDateBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_launch:
                    return com.fanc.wheretoplay.databinding.ActivityLaunchBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_recharge_detail:
                    return com.fanc.wheretoplay.databinding.ItemRechargeDetailBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_discount_coupon:
                    return com.fanc.wheretoplay.databinding.ItemDiscountCouponBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_search:
                    return com.fanc.wheretoplay.databinding.ItemSearchBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_reserve_list:
                    return com.fanc.wheretoplay.databinding.ItemReserveListBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_drinks:
                    return com.fanc.wheretoplay.databinding.FragmentDrinksBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_signin:
                    return com.fanc.wheretoplay.databinding.ActivitySigninBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.dialog_room_type:
                    return com.fanc.wheretoplay.databinding.DialogRoomTypeBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.dialog_map:
                    return com.fanc.wheretoplay.databinding.DialogMapBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_city_all:
                    return com.fanc.wheretoplay.databinding.ItemCityAllBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_housenews:
                    return com.fanc.wheretoplay.databinding.FragmentHousenewsBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_housetype:
                    return com.fanc.wheretoplay.databinding.ItemHousetypeBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_waiter_photo:
                    return com.fanc.wheretoplay.databinding.FragmentWaiterPhotoBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_discount_coupon:
                    return com.fanc.wheretoplay.databinding.FragmentDiscountCouponBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.dialog_picker:
                    return com.fanc.wheretoplay.databinding.DialogPickerBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_room_type:
                    return com.fanc.wheretoplay.databinding.ItemRoomTypeBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_reuse:
                    return com.fanc.wheretoplay.databinding.ActivityReuseBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_reserve:
                    return com.fanc.wheretoplay.databinding.ItemReserveBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.dialog_deal_detail_filter:
                    return com.fanc.wheretoplay.databinding.DialogDealDetailFilterBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_room:
                    return com.fanc.wheretoplay.databinding.FragmentRoomBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_pay_bill:
                    return com.fanc.wheretoplay.databinding.ActivityPayBillBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_room_type_dialog:
                    return com.fanc.wheretoplay.databinding.ItemRoomTypeDialogBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_mine:
                    return com.fanc.wheretoplay.databinding.FragmentMineBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_pop_filter:
                    return com.fanc.wheretoplay.databinding.ItemPopFilterBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_date_time:
                    return com.fanc.wheretoplay.databinding.ItemDateTimeBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_collection:
                    return com.fanc.wheretoplay.databinding.ItemCollectionBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_service_chat_left:
                    return com.fanc.wheretoplay.databinding.ItemServiceChatLeftBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_to_evaluate:
                    return com.fanc.wheretoplay.databinding.FragmentToEvaluateBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_check_comment:
                    return com.fanc.wheretoplay.databinding.ActivityCheckCommentBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_write_comment:
                    return com.fanc.wheretoplay.databinding.ItemWriteCommentBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_mine_info:
                    return com.fanc.wheretoplay.databinding.FragmentMineInfoBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.view_date_picker:
                    return com.fanc.wheretoplay.databinding.ViewDatePickerBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_consume_detail:
                    return com.fanc.wheretoplay.databinding.ItemConsumeDetailBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_alter_city:
                    return com.fanc.wheretoplay.databinding.ActivityAlterCityBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.view_topmenu:
                    return com.fanc.wheretoplay.databinding.ViewTopmenuBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_dialog_map:
                    return com.fanc.wheretoplay.databinding.ItemDialogMapBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_image:
                    return com.fanc.wheretoplay.databinding.ItemImageBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_waiter_info:
                    return com.fanc.wheretoplay.databinding.FragmentWaiterInfoBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_reserve:
                    return com.fanc.wheretoplay.databinding.FragmentReserveBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_advice_feedback:
                    return com.fanc.wheretoplay.databinding.FragmentAdviceFeedbackBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_drink:
                    return com.fanc.wheretoplay.databinding.ItemDrinkBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_integral_detail:
                    return com.fanc.wheretoplay.databinding.ItemIntegralDetailBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_deal_detail:
                    return com.fanc.wheretoplay.databinding.FragmentDealDetailBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_down_payment:
                    return com.fanc.wheretoplay.databinding.ActivityDownPaymentBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_about_us:
                    return com.fanc.wheretoplay.databinding.FragmentAboutUsBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_set_pay_pwd:
                    return com.fanc.wheretoplay.databinding.FragmentSetPayPwdBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_check_comments:
                    return com.fanc.wheretoplay.databinding.ItemCheckCommentsBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_pay:
                    return com.fanc.wheretoplay.databinding.ItemPayBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_comment:
                    return com.fanc.wheretoplay.databinding.FragmentCommentBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_referrals:
                    return com.fanc.wheretoplay.databinding.FragmentReferralsBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_message:
                    return com.fanc.wheretoplay.databinding.ItemMessageBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_signin:
                    return com.fanc.wheretoplay.databinding.FragmentSigninBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_pay:
                    return com.fanc.wheretoplay.databinding.FragmentPayBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_comment_icon:
                    return com.fanc.wheretoplay.databinding.ItemCommentIconBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_reset_pwd:
                    return com.fanc.wheretoplay.databinding.FragmentResetPwdBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_brief:
                    return com.fanc.wheretoplay.databinding.FragmentBriefBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_main:
                    return com.fanc.wheretoplay.databinding.ActivityMainBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_comment_label:
                    return com.fanc.wheretoplay.databinding.ItemCommentLabelBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_order_to_complete:
                    return com.fanc.wheretoplay.databinding.FragmentOrderToCompleteBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_comment_detail:
                    return com.fanc.wheretoplay.databinding.FragmentCommentDetailBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_city_hot_gridview:
                    return com.fanc.wheretoplay.databinding.ItemCityHotGridviewBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_collection:
                    return com.fanc.wheretoplay.databinding.FragmentCollectionBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.dialog_modify_gender:
                    return com.fanc.wheretoplay.databinding.DialogModifyGenderBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_comment:
                    return com.fanc.wheretoplay.databinding.ItemCommentBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_discover:
                    return com.fanc.wheretoplay.databinding.FragmentDiscoverBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_check_comments:
                    return com.fanc.wheretoplay.databinding.ActivityCheckCommentsBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_reserve_info:
                    return com.fanc.wheretoplay.databinding.FragmentReserveInfoBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_wallet:
                    return com.fanc.wheretoplay.databinding.FragmentWalletBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.dialog_alert:
                    return com.fanc.wheretoplay.databinding.DialogAlertBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_discover_image:
                    return com.fanc.wheretoplay.databinding.ActivityDiscoverImageBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.view_progress_dialog:
                    return com.fanc.wheretoplay.databinding.ViewProgressDialogBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.pop_filter:
                    return com.fanc.wheretoplay.databinding.PopFilterBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_message_detail:
                    return com.fanc.wheretoplay.databinding.FragmentMessageDetailBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_merchant_detail:
                    return com.fanc.wheretoplay.databinding.FragmentMerchantDetailBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_system_notify:
                    return com.fanc.wheretoplay.databinding.ItemSystemNotifyBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_check_comments_cardview:
                    return com.fanc.wheretoplay.databinding.ItemCheckCommentsCardviewBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_housenews:
                    return com.fanc.wheretoplay.databinding.ItemHousenewsBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_waiter:
                    return com.fanc.wheretoplay.databinding.ItemWaiterBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.dialog_modify_head:
                    return com.fanc.wheretoplay.databinding.DialogModifyHeadBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_search:
                    return com.fanc.wheretoplay.databinding.FragmentSearchBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_integral:
                    return com.fanc.wheretoplay.databinding.FragmentIntegralBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_city_search_result:
                    return com.fanc.wheretoplay.databinding.ItemCitySearchResultBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_deal_detail_list:
                    return com.fanc.wheretoplay.databinding.FragmentDealDetailListBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_large_image:
                    return com.fanc.wheretoplay.databinding.ActivityLargeImageBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_settings:
                    return com.fanc.wheretoplay.databinding.ActivitySettingsBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_detail:
                    return com.fanc.wheretoplay.databinding.ActivityDetailBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_search_result:
                    return com.fanc.wheretoplay.databinding.ItemSearchResultBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.fragment_date:
                    return com.fanc.wheretoplay.databinding.FragmentDateBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.activity_service:
                    return com.fanc.wheretoplay.databinding.ActivityServiceBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_discover:
                    return com.fanc.wheretoplay.databinding.ItemDiscoverBinding.bind(view, bindingComponent);
                case com.fanc.wheretoplay.R.layout.item_service_chat_right:
                    return com.fanc.wheretoplay.databinding.ItemServiceChatRightBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case -1416724307: {
                if(tag.equals("layout/pop_dialog_sheared_0")) {
                    return com.fanc.wheretoplay.R.layout.pop_dialog_sheared;
                }
                break;
            }
            case -748217218: {
                if(tag.equals("layout/fragment_register_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_register;
                }
                break;
            }
            case -1746065626: {
                if(tag.equals("layout/item_date_0")) {
                    return com.fanc.wheretoplay.R.layout.item_date;
                }
                break;
            }
            case 2100696623: {
                if(tag.equals("layout/activity_launch_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_launch;
                }
                break;
            }
            case 1503047859: {
                if(tag.equals("layout/item_recharge_detail_0")) {
                    return com.fanc.wheretoplay.R.layout.item_recharge_detail;
                }
                break;
            }
            case -613702162: {
                if(tag.equals("layout/item_discount_coupon_0")) {
                    return com.fanc.wheretoplay.R.layout.item_discount_coupon;
                }
                break;
            }
            case 458772704: {
                if(tag.equals("layout/item_search_0")) {
                    return com.fanc.wheretoplay.R.layout.item_search;
                }
                break;
            }
            case -567043591: {
                if(tag.equals("layout/item_reserve_list_0")) {
                    return com.fanc.wheretoplay.R.layout.item_reserve_list;
                }
                break;
            }
            case 1152118070: {
                if(tag.equals("layout/fragment_drinks_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_drinks;
                }
                break;
            }
            case -475061666: {
                if(tag.equals("layout/activity_signin_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_signin;
                }
                break;
            }
            case 1286768349: {
                if(tag.equals("layout/dialog_room_type_0")) {
                    return com.fanc.wheretoplay.R.layout.dialog_room_type;
                }
                break;
            }
            case -1929822437: {
                if(tag.equals("layout/dialog_map_0")) {
                    return com.fanc.wheretoplay.R.layout.dialog_map;
                }
                break;
            }
            case -741014267: {
                if(tag.equals("layout/item_city_all_0")) {
                    return com.fanc.wheretoplay.R.layout.item_city_all;
                }
                break;
            }
            case -1893467334: {
                if(tag.equals("layout/fragment_housenews_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_housenews;
                }
                break;
            }
            case -863782748: {
                if(tag.equals("layout/item_housetype_0")) {
                    return com.fanc.wheretoplay.R.layout.item_housetype;
                }
                break;
            }
            case 860993776: {
                if(tag.equals("layout/fragment_waiter_photo_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_waiter_photo;
                }
                break;
            }
            case -2038379157: {
                if(tag.equals("layout/fragment_discount_coupon_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_discount_coupon;
                }
                break;
            }
            case 176156913: {
                if(tag.equals("layout/dialog_picker_0")) {
                    return com.fanc.wheretoplay.R.layout.dialog_picker;
                }
                break;
            }
            case 1999350120: {
                if(tag.equals("layout/item_room_type_0")) {
                    return com.fanc.wheretoplay.R.layout.item_room_type;
                }
                break;
            }
            case 519749690: {
                if(tag.equals("layout/activity_reuse_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_reuse;
                }
                break;
            }
            case 1581801670: {
                if(tag.equals("layout/item_reserve_0")) {
                    return com.fanc.wheretoplay.R.layout.item_reserve;
                }
                break;
            }
            case -1328626506: {
                if(tag.equals("layout/dialog_deal_detail_filter_0")) {
                    return com.fanc.wheretoplay.R.layout.dialog_deal_detail_filter;
                }
                break;
            }
            case -829635146: {
                if(tag.equals("layout/fragment_room_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_room;
                }
                break;
            }
            case 1570134586: {
                if(tag.equals("layout/activity_pay_bill_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_pay_bill;
                }
                break;
            }
            case -2073698623: {
                if(tag.equals("layout/item_room_type_dialog_0")) {
                    return com.fanc.wheretoplay.R.layout.item_room_type_dialog;
                }
                break;
            }
            case -978359506: {
                if(tag.equals("layout/fragment_mine_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_mine;
                }
                break;
            }
            case -1296109218: {
                if(tag.equals("layout/item_pop_filter_0")) {
                    return com.fanc.wheretoplay.R.layout.item_pop_filter;
                }
                break;
            }
            case -1676697080: {
                if(tag.equals("layout/item_date_time_0")) {
                    return com.fanc.wheretoplay.R.layout.item_date_time;
                }
                break;
            }
            case 1826035926: {
                if(tag.equals("layout/item_collection_0")) {
                    return com.fanc.wheretoplay.R.layout.item_collection;
                }
                break;
            }
            case 579659342: {
                if(tag.equals("layout/item_service_chat_left_0")) {
                    return com.fanc.wheretoplay.R.layout.item_service_chat_left;
                }
                break;
            }
            case 1553306404: {
                if(tag.equals("layout/fragment_to_evaluate_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_to_evaluate;
                }
                break;
            }
            case -979727890: {
                if(tag.equals("layout/activity_check_comment_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_check_comment;
                }
                break;
            }
            case 1381762697: {
                if(tag.equals("layout/item_write_comment_0")) {
                    return com.fanc.wheretoplay.R.layout.item_write_comment;
                }
                break;
            }
            case -1136869215: {
                if(tag.equals("layout/fragment_mine_info_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_mine_info;
                }
                break;
            }
            case 757102875: {
                if(tag.equals("layout/view_date_picker_0")) {
                    return com.fanc.wheretoplay.R.layout.view_date_picker;
                }
                break;
            }
            case -710445268: {
                if(tag.equals("layout/item_consume_detail_0")) {
                    return com.fanc.wheretoplay.R.layout.item_consume_detail;
                }
                break;
            }
            case 1436047536: {
                if(tag.equals("layout/activity_alter_city_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_alter_city;
                }
                break;
            }
            case 224881488: {
                if(tag.equals("layout/view_topmenu_0")) {
                    return com.fanc.wheretoplay.R.layout.view_topmenu;
                }
                break;
            }
            case 918921821: {
                if(tag.equals("layout/item_dialog_map_0")) {
                    return com.fanc.wheretoplay.R.layout.item_dialog_map;
                }
                break;
            }
            case -2119805979: {
                if(tag.equals("layout/item_image_0")) {
                    return com.fanc.wheretoplay.R.layout.item_image;
                }
                break;
            }
            case 663921586: {
                if(tag.equals("layout/fragment_waiter_info_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_waiter_info;
                }
                break;
            }
            case 673666371: {
                if(tag.equals("layout/fragment_reserve_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_reserve;
                }
                break;
            }
            case -1460077197: {
                if(tag.equals("layout/fragment_advice_feedback_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_advice_feedback;
                }
                break;
            }
            case -2111608862: {
                if(tag.equals("layout/item_drink_0")) {
                    return com.fanc.wheretoplay.R.layout.item_drink;
                }
                break;
            }
            case -1955631282: {
                if(tag.equals("layout/item_integral_detail_0")) {
                    return com.fanc.wheretoplay.R.layout.item_integral_detail;
                }
                break;
            }
            case 1891132171: {
                if(tag.equals("layout/fragment_deal_detail_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_deal_detail;
                }
                break;
            }
            case 1037829157: {
                if(tag.equals("layout/activity_down_payment_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_down_payment;
                }
                break;
            }
            case -1742622933: {
                if(tag.equals("layout/fragment_about_us_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_about_us;
                }
                break;
            }
            case -1349738864: {
                if(tag.equals("layout/fragment_set_pay_pwd_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_set_pay_pwd;
                }
                break;
            }
            case -1490228541: {
                if(tag.equals("layout/item_check_comments_0")) {
                    return com.fanc.wheretoplay.R.layout.item_check_comments;
                }
                break;
            }
            case 1201688114: {
                if(tag.equals("layout/item_pay_0")) {
                    return com.fanc.wheretoplay.R.layout.item_pay;
                }
                break;
            }
            case 1450662438: {
                if(tag.equals("layout/fragment_comment_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_comment;
                }
                break;
            }
            case 757636349: {
                if(tag.equals("layout/fragment_referrals_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_referrals;
                }
                break;
            }
            case -1881166511: {
                if(tag.equals("layout/item_message_0")) {
                    return com.fanc.wheretoplay.R.layout.item_message;
                }
                break;
            }
            case 2069548093: {
                if(tag.equals("layout/fragment_signin_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_signin;
                }
                break;
            }
            case -444659409: {
                if(tag.equals("layout/fragment_pay_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_pay;
                }
                break;
            }
            case 1932340241: {
                if(tag.equals("layout/item_comment_icon_0")) {
                    return com.fanc.wheretoplay.R.layout.item_comment_icon;
                }
                break;
            }
            case -19215596: {
                if(tag.equals("layout/fragment_reset_pwd_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_reset_pwd;
                }
                break;
            }
            case -1183926527: {
                if(tag.equals("layout/fragment_brief_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_brief;
                }
                break;
            }
            case 423753077: {
                if(tag.equals("layout/activity_main_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_main;
                }
                break;
            }
            case -1928969122: {
                if(tag.equals("layout/item_comment_label_0")) {
                    return com.fanc.wheretoplay.R.layout.item_comment_label;
                }
                break;
            }
            case -2103021549: {
                if(tag.equals("layout/fragment_order_to_complete_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_order_to_complete;
                }
                break;
            }
            case 518584588: {
                if(tag.equals("layout/fragment_comment_detail_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_comment_detail;
                }
                break;
            }
            case -1384838021: {
                if(tag.equals("layout/item_city_hot_gridview_0")) {
                    return com.fanc.wheretoplay.R.layout.item_city_hot_gridview;
                }
                break;
            }
            case 1566340921: {
                if(tag.equals("layout/fragment_collection_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_collection;
                }
                break;
            }
            case 787494821: {
                if(tag.equals("layout/dialog_modify_gender_0")) {
                    return com.fanc.wheretoplay.R.layout.dialog_modify_gender;
                }
                break;
            }
            case -1936169559: {
                if(tag.equals("layout/item_comment_0")) {
                    return com.fanc.wheretoplay.R.layout.item_comment;
                }
                break;
            }
            case 1659251780: {
                if(tag.equals("layout/fragment_discover_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_discover;
                }
                break;
            }
            case -306772793: {
                if(tag.equals("layout/activity_check_comments_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_check_comments;
                }
                break;
            }
            case -1107502164: {
                if(tag.equals("layout/fragment_reserve_info_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_reserve_info;
                }
                break;
            }
            case 2081945300: {
                if(tag.equals("layout/fragment_wallet_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_wallet;
                }
                break;
            }
            case -888203365: {
                if(tag.equals("layout/dialog_alert_0")) {
                    return com.fanc.wheretoplay.R.layout.dialog_alert;
                }
                break;
            }
            case 855129217: {
                if(tag.equals("layout/activity_discover_image_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_discover_image;
                }
                break;
            }
            case 1361790518: {
                if(tag.equals("layout/view_progress_dialog_0")) {
                    return com.fanc.wheretoplay.R.layout.view_progress_dialog;
                }
                break;
            }
            case 150113820: {
                if(tag.equals("layout/pop_filter_0")) {
                    return com.fanc.wheretoplay.R.layout.pop_filter;
                }
                break;
            }
            case -856079260: {
                if(tag.equals("layout/fragment_message_detail_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_message_detail;
                }
                break;
            }
            case -1321696721: {
                if(tag.equals("layout/fragment_merchant_detail_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_merchant_detail;
                }
                break;
            }
            case -2111727261: {
                if(tag.equals("layout/item_system_notify_0")) {
                    return com.fanc.wheretoplay.R.layout.item_system_notify;
                }
                break;
            }
            case 1942186739: {
                if(tag.equals("layout/item_check_comments_cardview_0")) {
                    return com.fanc.wheretoplay.R.layout.item_check_comments_cardview;
                }
                break;
            }
            case -1053806083: {
                if(tag.equals("layout/item_housenews_0")) {
                    return com.fanc.wheretoplay.R.layout.item_housenews;
                }
                break;
            }
            case -184018534: {
                if(tag.equals("layout/item_waiter_0")) {
                    return com.fanc.wheretoplay.R.layout.item_waiter;
                }
                break;
            }
            case -1293842492: {
                if(tag.equals("layout/dialog_modify_head_0")) {
                    return com.fanc.wheretoplay.R.layout.dialog_modify_head;
                }
                break;
            }
            case -1648731965: {
                if(tag.equals("layout/fragment_search_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_search;
                }
                break;
            }
            case -780655289: {
                if(tag.equals("layout/fragment_integral_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_integral;
                }
                break;
            }
            case 2101098872: {
                if(tag.equals("layout/item_city_search_result_0")) {
                    return com.fanc.wheretoplay.R.layout.item_city_search_result;
                }
                break;
            }
            case -1322042668: {
                if(tag.equals("layout/fragment_deal_detail_list_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_deal_detail_list;
                }
                break;
            }
            case -1025066851: {
                if(tag.equals("layout/activity_large_image_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_large_image;
                }
                break;
            }
            case -415786017: {
                if(tag.equals("layout/activity_settings_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_settings;
                }
                break;
            }
            case 257710925: {
                if(tag.equals("layout/activity_detail_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_detail;
                }
                break;
            }
            case -1502033442: {
                if(tag.equals("layout/item_search_result_0")) {
                    return com.fanc.wheretoplay.R.layout.item_search_result;
                }
                break;
            }
            case -1243231287: {
                if(tag.equals("layout/fragment_date_0")) {
                    return com.fanc.wheretoplay.R.layout.fragment_date;
                }
                break;
            }
            case 1178002171: {
                if(tag.equals("layout/activity_service_0")) {
                    return com.fanc.wheretoplay.R.layout.activity_service;
                }
                break;
            }
            case -253325023: {
                if(tag.equals("layout/item_discover_0")) {
                    return com.fanc.wheretoplay.R.layout.item_discover;
                }
                break;
            }
            case 1934729527: {
                if(tag.equals("layout/item_service_chat_right_0")) {
                    return com.fanc.wheretoplay.R.layout.item_service_chat_right;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"city"
            ,"clcik"
            ,"click"
            ,"collection"
            ,"comment"
            ,"date"
            ,"detail"
            ,"discount"
            ,"doClick"
            ,"editText"
            ,"mapName"
            ,"message"
            ,"name"
            ,"notification"
            ,"order"
            ,"readed"
            ,"room"
            ,"rooms"
            ,"score"
            ,"search"
            ,"select"
            ,"store"
            ,"time"
            ,"type"
            ,"waiter"};
    }
}
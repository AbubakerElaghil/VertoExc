package vertoexcersice.alaqel.com.vertoexcersice.Respository.Server.Request;/*
package com.ultimate.pharmazadadmin.Respository.Server.Request;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.ultimate.pharmazadadmin.Respository.Server.Response.ResponseObject;
import com.ultimate.pharmazadadmin.Respository.Server.Response.Result;
import com.ultimate.pharmazadadmin.utilities.ConnectionDetector;
import com.ultimate.pharmazadadmin.utilities.RequestObject;
import com.ultimate.ultimatemarket.EventBusObjects.CartActivityEvent;
import com.ultimate.ultimatemarket.EventBusObjects.EventObject;
import com.ultimate.ultimatemarket.EventBusObjects.MyAddressesEvent;
import com.ultimate.ultimatemarket.EventBusObjects.RecentOrderEvent;
import com.ultimate.ultimatemarket.R;
import com.ultimate.ultimatemarket.Utilities.ConnectionDetector;
import com.ultimate.ultimatemarket.Utilities.ErrorMessages;


public class ServerRequests {

    public static ProgressDialog progressDialog;

    public static void sendRequestServerObject(int RqstCode, RequestObject requestObject, Context context) {

        if (ConnectionDetector.isConnectingToInternet(context)) {
            EventObject eventObject = new EventObject();
            switch (RqstCode) {
                case ServerRequestManager.GET_USER_DETAILS_REQUESTCODE:
                case ServerRequestManager.SAVE_ADDRESS_RQSTCODE:
                    eventObject = new MyAddressesEvent(null, RqstCode);
                    break;
                case ServerRequestManager.CANCEL_APPROVE_REJECT_ORDER_REQUESTCODE:
                    eventObject = new RecentOrderEvent(null, RqstCode);
                    break;
                case ServerRequestManager.GET_ORDER_CHARGES_REQUEST_CODE:
                    eventObject = new CartActivityEvent(null, RqstCode);
                    break;
                case ServerRequestManager.SAVE_INVOICE_REQUEST_CODE:
                    ShowProgress(context.getString(R.string.save_invoice), context.getString(R.string.loading_msg), context);
                    eventObject = new CartActivityEvent(null, RqstCode);
                    break;

                    case ServerRequestManager.GET_RECENT_ORDER_REQUESTCODE:
                        ShowProgress(context.getString(R.string.scrn_titl_rcnt_order), context.getString(R.string.loading_msg), context);
                    eventObject = new RecentOrderEvent(null, RqstCode);
                    break;


            }
            ServerRequestManager serverRequestManager = new ServerRequestManager(null, eventObject, context);
            serverRequestManager.request(requestObject);

        } else {

            Toast.makeText(context, context.getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();

        }


    }

    public static void ShowProgress(String Title, String Message, Context context) {
        if (progressDialog != null) {
            if (!progressDialog.isShowing()) {
                progressDialog = ProgressDialog.show(context, Title,
                        Message, true);
            }
        } else {
            progressDialog = ProgressDialog.show(context, Title,
                    Message, true);
        }


    }

    public static void HideProgress() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

    }


    public static boolean CheckResponse(ResponseObject responseObject) {
        if (responseObject == null) {
            return false;
        } else {
            Result result = responseObject.getResult();
            if (result != null) {
                if (result.getErrorNumber() == 0) {//Success
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }


    public static String getErrorMessage(ResponseObject responseObject, int RequestType, Context mcontext) {
      return  "";
       */
/* if (responseObject == null) {
            return "";
        } else {
            Result result = responseObject.getResult();
            if (result != null) {
                switch (RequestType) {
                    case ServerRequestManager.LOGIN_REQUEST_CODE:
                        return ErrorMessages.getLoginMessage(mcontext, result);
                    case ServerRequestManager.GET_ACTIVATION_CODE_REQUESTCODE:
                        return ErrorMessages.getActivationCodeMessage(mcontext, result);
                    case ServerRequestManager.SEND_ACTIVATION_CODE_REQUESTCODE:
                        return ErrorMessages.SendActivationCodeMessage(mcontext, result);
                    case ServerRequestManager.FORGOT_PASSWORD_REQUEST_CODE:
                        return ErrorMessages.ForgotPasswordMessage(mcontext, result);
                    case ServerRequestManager.REGISTER_REQUEST_CODE:
                        return ErrorMessages.getRegisterMessage(mcontext, result);

                    default:
                        return ErrorMessages.getDefaultMessage(mcontext, result);
                }

            } else {
                return "";
            }
        }*//*

    }

}
*/

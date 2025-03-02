package com.pichincha.infra.api.router;

public class RouterConsts {

    private RouterConsts(){}
    /**
     * componentes
     */
    public static final String COMPONENT_SCAN = "com.pichincha";

    /**
     * Controller config
     */
    public static final String API_ACCOUNTS = "Accounts";
    public static final String CROSS_ORIGIN = "*";
    public static final String CONTROLLER_ACCOUNTS_PATH = "/accounts";

    /**
     * operaciones o metodos
     */
    public static final String API_OPERATION_CREATE_ACCOUNT = "Create a new account";
    public static final String API_OPERATION_GET_ACCOUNT_BY_ID = "Get account information by id ";
    public static final String API_OPERATION_DELETE_ACCOUNT = "Delete account information";
    public static final String API_OPERATION_UPDATE_ACCOUNT = "Update account information";

    /**
     * descripcion de las operaciones o metodos
     */
    public static final String NOTE_API_OPERATION_CREATE_ACCOUNT = "In charge of create a new account.";
    public static final String NOTE_API_OPERATION_GET_BY_ID_ACCOUNT = "In charge of get account information filter by Id.";
    public static final String NOTE_API_OPERATION_DELETE_ACCOUNT = "In charge of delete a account.";
    public static final String NOTE_API_OPERATION_UPDATE_ACCOUNT = "In charge of update account infromation.";

    /**
     * mensajes de respuesta de acuerdo al codigo http
     */
    public static final String API_RESPONSE_COD_200 = "successful process";
    public static final String API_RESPONSE_COD_400 = "Some parameter is missing in the header";
    public static final String API_RESPONSE_COD_404 = "source not found.";
    public static final String API_RESPONSE_COD_422 = "Functional error in the application";
    public static final String API_RESPONSE_COD_500 = "Unknown error";

    /**
     * params
     */
    public static final String PARAM_ACCOUNT_ID = "account_id";

    /**
     * swagger param
     */
    public static final String API_PARAM_REQUEST_CREATE_ACCOUNT = "Body mapped to CreateAccountDto.";
    public static final String API_PARAM_REQUEST_UPDATE_ACCOUNT = "Body update mapped to CreateAccountDto.";
    public static final String API_PARAM_REQUEST_GET_ACCOUNT = "Id of account.";

    /**
     * messages
     */
    public static final String MSG_CONFIRMATION_DELETE = "Record deleted successful.";
    public static final String MSG_PROCESS_ACCOUNT = "%s %s account: %s.";
}

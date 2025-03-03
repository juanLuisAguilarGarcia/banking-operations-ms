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
    public static final String API_MOVEMENTS = "Movements";
    public static final String CROSS_ORIGIN = "*";
    public static final String CONTROLLER_ACCOUNTS_PATH = "/account";
    public static final String CONTROLLER_MOVEMENTS_PATH = "/movement";

    /**
     * operaciones o metodos
     */
    public static final String API_OPERATION_CREATE_ACCOUNT = "Create a new account";
    public static final String API_OPERATION_GET_ACCOUNT_BY_ID = "Get account information by id ";
    public static final String API_OPERATION_DELETE_ACCOUNT = "Delete account information";
    public static final String API_OPERATION_UPDATE_ACCOUNT = "Update account information";
    public static final String API_OPERATION_REPORT_ACCOUNT_STATE = "Account state information";

    public static final String API_OPERATION_CREATE_MOVEMENT = "Create a new movement";
    public static final String API_OPERATION_GET_MOVEMENT_BY_ID = "Get movement information by id ";
    public static final String API_OPERATION_DELETE_MOVEMENT = "Delete movement information";
    public static final String API_OPERATION_UPDATE_MOVEMENT = "Update movement information";

    /**
     * descripcion de las operaciones o metodos
     */
    public static final String NOTE_API_OPERATION_CREATE_ACCOUNT = "In charge of create a new account.";
    public static final String NOTE_API_OPERATION_GET_BY_ID_ACCOUNT = "In charge of get account information filter by Id.";
    public static final String NOTE_API_OPERATION_DELETE_ACCOUNT = "In charge of delete a account.";
    public static final String NOTE_API_OPERATION_UPDATE_ACCOUNT = "In charge of update account information.";
    public static final String NOTE_API_OPERATION_REPORT_ACCOUNT_STATE = "In charge of get account state information filter by  date.";

    public static final String NOTE_API_OPERATION_CREATE_MOVEMENT = "In charge of create a new movement.";
    public static final String NOTE_API_OPERATION_GET_BY_ID_MOVEMENT = "In charge of get movement information filter by Id.";
    public static final String NOTE_API_OPERATION_DELETE_MOVEMENT = "In charge of delete a movement.";
    public static final String NOTE_API_OPERATION_UPDATE_MOVEMENT = "In charge of update movement information.";
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
    public static final String PARAM_MOVEMENT_ID = "movement_id";
    public static final String PARAM_CLIENT_ID = "client_id";
    public static final String PARAM_INIT_DATE = "init_date";
    public static final String PARAM_END_DATE = "end_date";

    /**
     * swagger param
     */
    public static final String API_PARAM_REQUEST_CREATE_ACCOUNT = "Body mapped to CreateAccountDto.";
    public static final String API_PARAM_REQUEST_UPDATE_ACCOUNT = "Body update mapped to CreateAccountDto.";
    public static final String API_PARAM_REQUEST_GET_ACCOUNT = "Id of account.";
    public static final String API_PARAM_REQUEST_INIT_DATE = "Init date.";
    public static final String API_PARAM_REQUEST_END_DATE = "End date.";

    public static final String API_PARAM_REQUEST_CREATE_MOVEMENT = "Body mapped to CreateAccountDto.";
    public static final String API_PARAM_REQUEST_UPDATE_MOVEMENT = "Body update mapped to CreateAccountDto.";
    public static final String API_PARAM_REQUEST_GET_MOVEMENT = "Id of movement.";

    /**
     * messages
     */
    public static final String MSG_CONFIRMATION_DELETE = "Record deleted successful.";
    public static final String MSG_PROCESS_ACCOUNT = "%s %s account: %s.";
    public static final String MSG_PROCESS_MOVEMENT = "%s %s movement: %s.";
}

package com.spring_async.spring_async.utility;

public interface Constant {
    String BEARER = "Bearer ";
    String FEATURE_PREFIX = "FEATURE_";
    String[] USER_TYPE = {"Utility", "Consumer"};
    String BEARER_AUTH = "bearerAuth";
    String[] SKIP_URLS = {"/webjars/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**"};
    String SUCCESS = "success";
    String ERROR = "error";
    String TRY_AGAIN_LATER = "Please try again later";
    String SUCCESS_CRATE = "Successfully Created";
    String SUCCESS_UPDATE = "Successfully Updated";
    String SUCCESS_DELETE = "Successfully Deleted";
    String SUCCESS_UPLOADED = "Successfully Uploaded";
    String BASE_PATH = "/api/energy";
    String BILLING_TEMPLATE_URL = BASE_PATH + "/billingTemplate";
    Integer DEFAULT_PAGE_SIZE = 10;

    String ENERGY_BILLING_PARAMETER_URL = BASE_PATH + "/energyBillingParameter";

    String CONSUMER_URL = BASE_PATH + "/consumer";

    String UNASSIGNED_METERS = BASE_PATH + "/unAssignedMeter";
    String METERS_DETAILS = BASE_PATH + "/meter";
    String METER_TYPE_URL = BASE_PATH + "/meterTypes";
    String VENDORS = BASE_PATH + "/vendors";

    String TARIFF_URL = BASE_PATH + "/tariff";

    String BILLING_CYCLE_URL = BASE_PATH + "/billingCycle";

    String TARIFF_SCHEDULE_URL = BASE_PATH + "/tariffSchedule";

    String CATEGORY_URL = BASE_PATH + "/category";

    String TARIFF_ENERGY_BILL_PARAM_MAP_URL = BASE_PATH + "/tariffEnergyBillParamMap";



}

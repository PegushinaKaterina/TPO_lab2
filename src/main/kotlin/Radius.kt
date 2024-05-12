enum class Radius(val buttonXPath: String, val text: String) {
    NO_RADIUS(
        "//*[text()=\"No radius\"]/ancestor::button[@data-xds=\"SingleSelect\"]",
        "No radius"
    ),
    KM_10(
        "//*[text()=\"10 km\"]/ancestor::button[@data-xds=\"SingleSelect\"]",
        "10 km"
    ),
    KM_20(
        "//*[text()=\"20 km\"]/ancestor::button[@data-xds=\"SingleSelect\"]",
        "20 km"
    ),
    KM_50(
        "//*[text()=\"50 km\"]/ancestor::button[@data-xds=\"SingleSelect\"]",
        "50 km"
    ),
    KM_70(
        "//*[text()=\"70 km\"]/ancestor::button[@data-xds=\"SingleSelect\"]v",
        "70 km"
    ),
    KM_100(
        "//*[text()=\"100 km\"]/ancestor::button[@data-xds=\"SingleSelect\"]",
        "100 km"
    ),
    KM_200(
        "//*[text()=\"200 km\"]/ancestor::button[@data-xds=\"SingleSelect\"]",
        "200 km"
    ),
}
enum class Radius(val radiusOptionButtonXPath: String, val text: String) {
    NO_RADIUS(
        "//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/section/div/div/form/div[2]/div[2]/div/button[1]/div",
        "No radius"
    ),
    KM_10(
        "//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/section/div/div/form/div[2]/div[2]/div/button[2]/div",
        "10 km"
    ),
    KM_20(
        "//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/section/div/div/form/div[2]/div[2]/div/button[3]/div",
        "20 km"
    ),
    KM_50(
        "//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/section/div/div/form/div[2]/div[2]/div/button[4]/div",
        "50 km"
    ),
    KM_70(
        "//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/section/div/div/form/div[2]/div[2]/div/button[5]/div",
        "70 km"
    ),
    KM_100(
        "//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/section/div/div/form/div[2]/div[2]/div/button[6]/div",
        "100 km"
    ),
    KM_200(
        "//*[@id=\"app\"]/div/div[2]/div/div/div[1]/div/section/div/div/form/div[2]/div[2]/div/button[7]/div",
        "200 km"
    ),
}
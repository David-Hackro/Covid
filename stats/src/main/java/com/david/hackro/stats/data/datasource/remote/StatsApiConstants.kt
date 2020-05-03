package com.david.hackro.stats.data.datasource.remote

class StatsApiConstants {

    object EndPointParams {
        const val QUERY_JSON_FORMAT = "json"
        const val QUERY_JSON_FORMAT_VALUE = "json"
        const val QUERY_NAME = "name"
        const val QUERY_CODE = "code"
        const val QUERY_DATE = "date"
        const val QUERY_DATE_FORMAT = "date-format"
        const val DATE_FORMAT = "yyyy-MM-dd"
    }

    object EndPointPaths {
        const val TOTALS = "totals"
        const val ALL = "all"
        const val COUNTRY = "country"
        const val COUNTRIES = "countries"
        const val CODE = "code"
        const val NAME = "name"
        const val HELP = "help"

        const val DATA_LATEST = "data"
        const val DATA_LATEST_VALUE = "data_latest.json"
    }
}

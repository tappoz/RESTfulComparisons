package main

import (
	"encoding/json"
	"github.com/Sirupsen/logrus"
	"github.com/gorilla/mux"
	"net/http"
)

var logger = logrus.New()

func init() {
	logger.Level = logrus.DebugLevel
	// cfr.: https://github.com/Sirupsen/logrus/blob/master/text_formatter.go#L35
	logger.Formatter = &logrus.TextFormatter{FullTimestamp: true}
}

func main() {

	router := mux.NewRouter()

	router.HandleFunc("/ticker", GetTickerCode).Methods("GET")
	router.HandleFunc("/ticker/{tickerCode}", GetTickerCode).Methods("GET")
	http.Handle("/", router)

	logger.Info("Just set up the routes")

	if err := http.ListenAndServe(":8080", nil); err != nil {
		logger.Fatal("http.ListenAndServe: ", err)
	}
}

func GetTickerCode(w http.ResponseWriter, r *http.Request) {

	pathParams := mux.Vars(r)
	logger.Debug("Vars:", pathParams)

	tickerCode := pathParams["tickerCode"]
	if tickerCode == "" {
		logger.Info("The HTTP path parameter was not found!")
		tickerCode = r.URL.Query().Get("tickerCode")
		logger.Info("The HTTP query parameter tickerCode is:", tickerCode)
	}
	logger.Debug("Requested ticker:", tickerCode)

	w.Header().Set("Content-Type", "application/json; charset=UTF-8")
	w.WriteHeader(http.StatusOK)

	var quandlTicker QuandlTicker
	quandlTicker = GetRemoteJson()
	if quandlTicker.Code == "" {
		logger.Warn("The remote JSON retrieval was empty, using the local sample instead")
		quandlTicker = GetJsonSampleFromFile()
	}
	logger.Debug("The JSON sample found is for the ticker:", quandlTicker.Code)

	quandlPresObj := QuandlPresentation{}
	quandlPresObj = quandlTicker.ToPresentationStruct()
	logger.Debug("quandlPresObj:", quandlPresObj)

	if err := json.NewEncoder(w).Encode(quandlPresObj); err != nil {
		panic(err)
	}

}

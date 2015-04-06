package main

import (
	"encoding/json"
	"github.com/codegangsta/negroni"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

func main() {

	router := mux.NewRouter()

	router.HandleFunc("/ticker", GetTickerCode).Methods("GET")
	router.HandleFunc("/ticker/{tickerCode}", GetTickerCode).Methods("GET")

	n := negroni.Classic()
	n.UseHandler(router)
	n.Run(":8080")
}

func GetTickerCode(w http.ResponseWriter, r *http.Request) {

	pathParams := mux.Vars(r)
	log.Println("Vars:", pathParams)

	tickerCode := pathParams["tickerCode"]
	if tickerCode == "" {
		log.Println("The HTTP path parameter was not found!")
		tickerCode = r.URL.Query().Get("tickerCode")
		log.Println("The HTTP query parameter tickerCode is:", tickerCode)
	}
	log.Println("Requested ticker:", tickerCode)

	w.Header().Set("Content-Type", "application/json; charset=UTF-8")
	w.WriteHeader(http.StatusOK)

	var quandlTicker QuandlTicker
	quandlTicker = GetRemoteJson()
	if quandlTicker.Code == "" {
		log.Println("The remote JSON retrieval was empty, using the local sample instead")
		quandlTicker = GetJsonSampleFromFile()
	}
	log.Println("The JSON sample found is for the ticker:", quandlTicker.Code)

	quandlPresObj := QuandlPresentation{}
	quandlPresObj = quandlTicker.ToPresentationStruct()
	log.Println("quandlPresObj:", quandlPresObj)

	if err := json.NewEncoder(w).Encode(quandlPresObj); err != nil {
		panic(err)
	}

}

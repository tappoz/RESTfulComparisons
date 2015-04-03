package main

import (
	"encoding/json"
	"github.com/codegangsta/negroni"
	"github.com/gorilla/mux"
	"io/ioutil"
	"log"
	"net/http"
)

func main() {

	router := mux.NewRouter()

	router.HandleFunc("/ticker", getTickerCode).Methods("GET")
	router.HandleFunc("/ticker/{tickerCode}", getTickerCode).Methods("GET")

	n := negroni.Classic()
	n.UseHandler(router)
	n.Run(":8080")
}

func getTickerCode(w http.ResponseWriter, r *http.Request) {

	vars := mux.Vars(r)

	tickerCode := vars["tickerCode"]
	if tickerCode == "" {
		log.Println("The path parameter was not found!")
		tickerCode = r.URL.Query().Get("tickerCode")
		log.Println("The query parameter tickerCode is:", tickerCode)
	}
	log.Println("Requested ticker:", tickerCode)

	w.Header().Set("Content-Type", "application/json; charset=UTF-8")
	w.WriteHeader(http.StatusOK)

	// quandlTicker := QuandlTicker{Code: tickerCode}
	// if err := json.NewEncoder(w).Encode(quandlTicker); err != nil {
	// 	panic(err)
	// }

	contentSample, err := ioutil.ReadFile("sampleQuandlTicker.json")
	if err != nil {
		log.Println(err)
	}

	var quandlTicker QuandlTicker
	err = json.Unmarshal(contentSample, &quandlTicker)
	if err != nil {
		log.Println(err)
	}

	quandlPresObj := QuandlPresentation{}
	quandlPresObj = quandlTicker.ToPresentationStruct()
	log.Println("quandlPresObj:", quandlPresObj)

	if err := json.NewEncoder(w).Encode(quandlTicker.ToPresentationStruct()); err != nil {
		// if err := json.NewEncoder(w).Encode(quandlTicker); err != nil {
		panic(err)
	}

}

package main

import (
	"encoding/json"
	"io/ioutil"
	"log"
	// "strings"
)

type QuandlTicker struct {
	// TODO error field
	Id          uint32          `json:"id"`
	SourceName  string          `json:"source_name"`
	SourceCode  string          `json:"source_code"`
	Code        string          `json:"code"`
	Name        string          `json:"name"`
	UrlizeName  string          `json:"urilize_name"`
	DisplayUrl  string          `json:"display_url"`
	Description string          `json:"description"`
	UpdatedAt   string          `json:"updated_at"`
	Frequency   string          `json:"frequency"`
	FromDate    string          `json:"from_date"`
	ToDate      string          `json:"to_date"`
	ColumnNames []string        `json:"column_names"`
	Private     bool            `json:"private"`
	Type        string          `json:"type"`
	Premium     bool            `json:"premium"`
	Data        [][]interface{} `json:"data"`
}

type QuandlPresentation struct {
	TickerCode     string                 `json:"tickerCode"`
	Frequency      string                 `json:"frequency"`
	DailyStockData []QuandlDailyStockData `json:"dailyStockData"`
}

type QuandlDailyStockData struct {
	Date         string  `json:"date"`
	ClosingPrice float64 `json:"closingPrice"`
}

func (qTicker *QuandlTicker) ToPresentationStruct() QuandlPresentation {

	quandlDailyStockData := QuandlDailyStockData{}

	closingDate := qTicker.Data[0][0]
	log.Println("closingDate:", closingDate)
	quandlDailyStockData.Date = closingDate.(string)
	closingPrice := qTicker.Data[0][1]
	log.Println("closingPrice:", closingPrice)
	quandlDailyStockData.ClosingPrice = closingPrice.(float64)
	log.Println("quandlDailyStockData:", quandlDailyStockData)

	qPresentation := QuandlPresentation{TickerCode: qTicker.Code, Frequency: qTicker.Frequency, DailyStockData: []QuandlDailyStockData{quandlDailyStockData}}

	log.Println("About to return the adapted:", qPresentation)
	return qPresentation
}

func GetJsonSampleFromFile() QuandlTicker {

	contentSample, err := ioutil.ReadFile("sampleQuandlTicker.json")
	if err != nil {
		log.Println(err)
	}

	var quandlTicker QuandlTicker
	err = json.Unmarshal(contentSample, &quandlTicker)
	if err != nil {
		log.Println(err)
	}

	return quandlTicker
}

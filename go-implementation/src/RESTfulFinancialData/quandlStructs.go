package main

import (
	"log"
)

type QuandlTicker struct {
	// TODO error field
	Id          uint32        `json:"id"`
	SourceName  string        `json:"source_name"`
	SourceCode  string        `json:"source_code"`
	Code        string        `json:"code"`
	Name        string        `json:"name"`
	UrlizeName  string        `json:"urilize_name"`
	DisplayUrl  string        `json:"display_url"`
	Description string        `json:"description"`
	UpdatedAt   string        `json:"updated_at"`
	Frequency   string        `json:"frequency"`
	FromDate    string        `json:"from_date"`
	ToDate      string        `json:"to_date"`
	ColumnNames []string      `json:"column_names"`
	Private     bool          `json:"private"`
	Type        string        `json:"type"`
	Premium     bool          `json:"premium"`
	Data        []interface{} `json:"data"`
}

type QuandlPresentation struct {
	TickerCode     string                 `json:"tickerCode"`
	Frequency      string                 `json:"frequency"`
	DailyStockData []QuandlDailyStockData `json:"dailyStockData"`
}

type QuandlDailyStockData struct {
	Date         string  `json:"date"`
	ClosingPrice float32 `json:"closingPrice"`
}

func (qTicker *QuandlTicker) ToPresentationStruct() QuandlPresentation {

	qPresentation := QuandlPresentation{}
	qPresentation.TickerCode = qTicker.Code

	log.Println("About to return the adapted:", qPresentation)
	return qPresentation
}

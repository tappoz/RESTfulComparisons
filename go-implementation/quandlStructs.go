package main

import (
	"encoding/json"
	"io/ioutil"
	"net/http"
	"time"
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

	logger.Info("qTicker.Data length: ", len(qTicker.Data))

	quandlDailyStockData := Map(&qTicker.Data)
	logger.Debug("quandlDailyStockData:", quandlDailyStockData)

	qPresentation := QuandlPresentation{TickerCode: qTicker.Code, Frequency: qTicker.Frequency, DailyStockData: quandlDailyStockData}

	logger.Debug("About to return the adapted:", qPresentation)
	return qPresentation
}

func GetJsonSampleFromFile() QuandlTicker {

	contentSample, err := ioutil.ReadFile("sampleQuandlTicker.json")
	if err != nil {
		logger.Error(err)
	}

	var quandlTicker QuandlTicker
	err = json.Unmarshal(contentSample, &quandlTicker)
	if err != nil {
		logger.Error(err)
	}

	return quandlTicker
}

func ToDailyStockStruct(qData *[]interface{}) QuandlDailyStockData {

	logger.Debug("qData:", qData)
	quandlDailyStockData := QuandlDailyStockData{}

	closingDate := (*qData)[0]
	logger.Debug("closingDate:", closingDate)
	quandlDailyStockData.Date = closingDate.(string)
	closingPrice := (*qData)[1]
	logger.Debug("closingPrice:", closingPrice)
	quandlDailyStockData.ClosingPrice = closingPrice.(float64)

	logger.Debug("quandlDailyStockData:", quandlDailyStockData)
	return quandlDailyStockData

}

func Map(qData *[][]interface{}) []QuandlDailyStockData {

	logger.Info("qData length: ", len(*qData))

	outputDailyStockData := make([]QuandlDailyStockData, len(*qData))

	for dataIndex, dataValue := range *qData {
		logger.Debugf("At index %d the array has value: %v", dataIndex, dataValue)
		outputDailyStockData[dataIndex] = ToDailyStockStruct(&dataValue)
	}

	logger.Info("About to return a []QuandlDailyStockData of length:", len(outputDailyStockData))
	return outputDailyStockData

}

func GetRemoteJson() QuandlTicker {

	timeout := time.Duration(6 * time.Second)
	client := http.Client{
		Timeout: timeout,
	}

	var quandlTicker QuandlTicker
	resp, err := client.Get("https://www.quandl.com/api/v1/datasets/WIKI/AAPL.json?trim_start=2015-01-01&trim_end=2015-03-14&column=4")
	if err != nil {
		logger.Error("Issues in retrieving the remote JSON object")
		return quandlTicker
	}
	defer resp.Body.Close()
	remoteContentBody, err := ioutil.ReadAll(resp.Body)
	logger.Debug("remoteContentBody:", remoteContentBody)

	err = json.Unmarshal(remoteContentBody, &quandlTicker)
	if err != nil {
		logger.Error(err)
	}

	logger.Debug("About to return a remotely retrieved quandlTicker:", quandlTicker)
	return quandlTicker

}

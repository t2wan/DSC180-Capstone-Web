let input = document.querySelector('input')

let textarea = document.querySelector('textarea') 


  
// This event listener has been implemented to identify a 
// Change in the input section of the html code 
// It will be triggered when a file is chosen. 
input.addEventListener('change', () => { 
    let files = input.files; 
  
    if (files.length == 0) return; 
  
    /* If any further modifications have to be made on the 
       Extracted text. The text can be accessed using the  
       file variable. But since this is const, it is a read  
       only variable, hence immutable. To make any changes,  
       changing const to var, here and In the reader.onload  
       function would be advisible */
    const file = files[0]; 
  
    let reader = new FileReader(); 
  
    reader.onload = (e) => { 
        const file = e.target.result; 
  
        // This is a regular expression to identify carriage  
        // Returns and line breaks 
        const lines = file.split(/\r\n|\n/); 
        textarea.value = lines.join('\n'); 
        console.log(lines.join('\n').split(" ").slice(0,5))


        var myChart = Highcharts.chart('container', {
            chart: {
                type: 'column'
            },
            title: {
                text: 'EDA Trial'
            },
            xAxis: {
                categories: lines.join('\n').split(" ").slice(0,2)
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'words frequency'
                }
            },
            // legend: {
            //     reversed: true
            // },
            plotOptions: {
                series: {
                    stacking: 'normal'
                }
            },
            series: [{
                name: lines.join('\n').split(" ").slice(0,1),
                data: [1,0]
            }, {
                name: lines.join('\n').split(" ").slice(1,2),
                data: [0,1]
            }]
        });

 
        data = Highcharts.reduce(lines, function (arr, word) {
        var obj = Highcharts.find(arr, function (obj) {
            return obj.name === word;
        });
        if (obj) {
            obj.weight += 1;
        } else {
            obj = {
                name: word,
                weight: 1
            };
            arr.push(obj);
        }
        return arr;
    }, []);

    Highcharts.chart('container3', {
        accessibility: {
            screenReaderSection: {
                beforeChartFormat: '<h5>{chartTitle}</h5>' +
                    '<div>{chartSubtitle}</div>' +
                    '<div>{chartLongdesc}</div>' +
                    '<div>{viewTableButton}</div>'
            }
        },
        series: [{
            type: 'wordcloud',
            data: data,
            // name: 'Occurrences'
        }],
        title: {
            text: 'Wordcloud'
        }
});
        


    }
    reader.onerror = (e) => alert(e.target.error.name); 
  
    reader.readAsText(file); 
}); 
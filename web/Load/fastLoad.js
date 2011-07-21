function showTeam(){
    new Ext.Window({
        width: 400,
        height: 400,
        title: 'Our team!',
        layout:'fit',

        items: [new Ext.Panel({
            padding:5,
            html:'<b>Leader:</b><br>\n\
* Merter Hami Karacan<br>\n\
<br><b>Developing:</b><br>\n\
* Yunus Emre Hendekci<br>\n\
* Ugur Kocager<br>\n\
* Ridvan Ozbugru<br>\n\
* Onur Salih Gokdere<br>\n\
* Mustafa Agah Ozturk<br>\n\
* Mesut Aydin<br>\n\
* Mehmet Kurt<br>\n\
* Ibrahim Bozan<br>\n\
* Ahmet Aslan<br>\n\
* Abdullah Sener<br>\n\
<br><b>Source Research:</b><br>\n\
* Yagmur Kilic<br>\n\
* Rukiye Ubuntu<br>\n\
* Songul Aktepe<br>\n\
* Serpil Gul<br>\n\
'
        })]
    }).show();
}

function loadPublications(urlpt){
    var pubDisplay = new Ext.form.DisplayField({
        value:'<img src="images/loading.gif"> Publications loading...',
        renderTo:'content'
    });

    Ext.Ajax.request({
        timeout:180000,
        url: 'Load/_publications.jsp?urlpt='+urlpt,
        success: function(response, opts) {
            var obj = Ext.decode(response.responseText);
            pubDisplay.setValue(obj.message+' <img src="images/tick.png">');
            loadCategories();
        },
        failure: function(response, opts) {
            pubDisplay.setValue('Publications loading failed.');
        }
    });
}

function loadCategories(){
    var catDisplay = new Ext.form.DisplayField({
        value:'<img src="images/loading.gif"> Categories analyzing and loading... (~Publication * 1 second.)',
        renderTo:'content'
    });
    Ext.Ajax.request({
        timeout:180000,
        url: 'Load/_categories.jsp',
        success: function(response, opts) {
            var obj = Ext.decode(response.responseText);


            if(obj.success==true){
                catDisplay.setValue(obj.message+' <img src="images/tick.png">');
                chartLinks();
            }else{
                catDisplay.setValue(obj.message+' <img src="images/exclamation.png">');
            }

        },
        failure: function(response, opts) {
            catDisplay.setValue('Categories analyzing failed.');
        }
    });
}

function loadCoauthors(){
    var coaDisplay = new Ext.form.DisplayField({
        value:'<img src="images/loading.gif"> Coauthors loading...',
        renderTo:'content'
    });
    Ext.Ajax.request({
        url: 'Load/_coauthors.jsp',
        timeout:180000,
        success: function(response, opts) {
            var obj = Ext.decode(response.responseText);
            if(obj.success==true){
                coaDisplay.setValue(obj.message+' <img src="images/tick.png">');
                //loadMoreCoauthors();
                askForMoreCoauthors();
            }else{
                coaDisplay.setValue(obj.message+' <img src="images/exclamation.png">');
            }
        },
        failure: function(response, opts) {
            coaDisplay.setValue('Coauthors loading failed.');
        }
    });
}

function askForMoreCoauthors(){
    var moreauthorsDisplay = new Ext.form.DisplayField({
        value:'<a href="javascript:void(0);" onclick="loadMoreCoauthors();">Start to more authors analyze..</a> <img src="images/chart_organisation.png" align="middle">',
        renderTo:'content'
    });

}

function loadMoreCoauthors(){
    var mocoaDisplay = new Ext.form.DisplayField({
        value:'<img src="images/loading.gif"> More Coauthors loading...',
        renderTo:'content'
    });
    Ext.Ajax.request({
        timeout:180000,
        url: 'Load/_morecoauthors.jsp',
        success: function(response, opts) {
            var obj = Ext.decode(response.responseText);
            if(obj.success==true){
                mocoaDisplay.setValue(obj.message+' <img src="images/tick.png">');
                loadMoreCoauthorPublication();
            }
            else
                mocoaDisplay.setValue(obj.message);
        },
        failure: function(response, opts) {
        //            mocoaDisplay.setValue('More Coauthors loading failed.');
        }
    });
}

function loadMoreCoauthorPublication(){
    var pbar2 = new Ext.ProgressBar({
        text:'Loading more co-authors\' publications...' ,
        id:'morepublications',
        cls:'left-align',
        renderTo:'content'
    });

    /**/
    var moreCount=0;
    var  complate=0;
    var  pubs = 0;
    var  run=0;
    var  stop=0;
    var  i=0;
    function loadMoreCoPublication(){
        
        if(stop==run && complate>90){
            pbar2.updateProgress(complate, 'More Publications loaded.. (Collected: '+pubs+')');
            loadMoreCoauthorCategory();
        }

        if(moreCount>5){
            return;
        }
        if(i>100){
            return;
        }


        run++;
        moreCount++;

        Ext.Ajax.request({
            timeout:180000,
            url: 'Load/_morepublications.jsp?load='+i,
            success: function(response, opts) {
                stop++;
                var obj = Ext.decode(response.responseText);
                pubs+=obj.count;
                moreCount--;
                //                if(obj.success==true){
                complate++;
                pbar2.updateProgress(complate/100, 'Loading publications of ' + complate + ' of 100 person... (Collected: '+pubs+')');
                //                }
                if(moreCount<5){
                    i++;
                    loadMoreCoPublication();
                }

            },
            failure: function(response, opts) {
                stop++;
                moreCount--;
                if(moreCount<5){
                    i++;
                    loadMoreCoPublication();
                }
            //                pbar2.setValue('More Coauthors loading failed.');
            }
        });
    }
    /**/

    for(i = 0; i<5; i++){
        loadMoreCoPublication();
    }

}

function loadMoreCoauthorCategory(){
    var pbar3 = new Ext.ProgressBar({
        text:'Analyzing more co-authors\' publications...' ,
        id:'morecategories',
        cls:'left-align',
        renderTo:'content'
    });

    /**/
    var  moreCount=0;
    var  complate=0;
    var  pubs = 0;
    var  run=0;
    var  stop=0;
    var  i=0;
    function loadMoreCoCategory(){

        if(stop==run){
            pbar3.updateProgress(complate, 'More Category loaded.. (Collected: '+pubs+')');
        }

        if(moreCount>5){
            return;
        }

        if(i>100){
            return;
        }


        run++;
        moreCount++;

        Ext.Ajax.request({
            timeout:180000,
            url: 'Load/_morecategories.jsp?load='+i,
            success: function(response, opts) {
                stop++;
                var obj = Ext.decode(response.responseText);
                if(obj.count>0)
                    pubs+=obj.count;
                moreCount--;
                //                if(obj.success==true){
                complate++;
                pbar3.updateProgress(complate/100, 'Category analyzed of ' + complate + ' of 100 person... (Collected: '+pubs+')');
                //                }
                if(moreCount<5){
                    i++;
                    loadMoreCoCategory();
                }

            },
            failure: function(response, opts) {
                stop++;
                moreCount--;
                if(moreCount<5){
                    i++;
                    loadMoreCoCategory();
                }
            //                pbar3.updateProgress(complate/100,'More Coauthors loading failed.');
            }
        });
    }
    /**/

    for(i = 0; i<5; i++){
        loadMoreCoCategory();
    }

}

function chartLinks(){
    var wordsDisplay = new Ext.form.DisplayField({
        value:'<a href="javascript:void(0);" onclick="mostSignificantWords();">Most used keys chart.</a> <img src="images/chart_pie.png" align="middle">',
        renderTo:'content'
    });

}

function mostSignificantWords(){
    new Ext.Window({
        width: 400,
        height: 400,
        title: 'Most Significant Words',
        items: {
            store: new Ext.data.Store({
                autoLoad:true,
                reader: new Ext.data.JsonReader({
                    fields: [
                    'word','count'
                    ]
                }),
                proxy: new Ext.data.HttpProxy(new Ext.data.Connection({
                    url: 'Data/_mostsignificantwords.jsp',
                    timeout:90000
                }))
            }),
            xtype: 'piechart',
            dataField: 'count',
            categoryField: 'word',
            //extra styles get applied to the chart defaults
            extraStyle:
            {
                legend:
                {
                    display: 'bottom',
                    padding: 5,
                    font:
                    {
                        family: 'Calibri',
                        size: 13
                    }
                }
            }
        }
    }).show();
}
var urlpt="";
var tabPanel;
var searchCombo;
Ext.onReady(function(){


    var ds = new Ext.data.Store({
        proxy: new Ext.data.ScriptTagProxy({
            url: '_search.jsp'
        }),
        reader: new Ext.data.JsonReader({
            root: 'root',
            totalProperty: 'total',
            id: 'name'
        }, [ 'name','url' ])
    });

    var resultTpl = new Ext.XTemplate(
        '<tpl for="."><div class="search-item">',
        '{name}',
        '</div></tpl>'
        );

    searchCombo = new Ext.form.ComboBox({
        store: ds,
        displayField:'name',
        typeAhead: false,
        loadingText: 'Searching...',
        listEmptyText:'Not found..',
        emptyText:'type here..',
        width: 500,
        pageSize:10,
        hideTrigger:false,
        tpl: resultTpl,
        renderTo: 'searchfield',
        itemSelector: 'div.search-item',
        onSelect: function(record){
            clearContent();
            searchCombo.collapse();
            searchCombo.setValue(record.data.name);
            new Ext.form.DisplayField({
                value:'<br>Analyse of <b>'+record.data.name+'</b>',
                renderTo:'content'
            });
            urlpt=record.data.url;
            //publicationLoad(record.data.url);
            loadPublications(record.data.url);
            loadCoauthors(record.data.url);
        }
    });
    searchThis("Mehmet Kaya");
});

function searchThis(str){
    //    alert(str);
    searchCombo.focus();
    searchCombo.setValue(str);
    searchCombo.expand();
    //    searchCombo.fireEvent('keyrelease');
    searchCombo.getStore().reload({
        params:{
            query:str,
            limit:15,
            start:0
        }
    });
    if(Ext.getCmp('pubWin')!=null)
        Ext.getCmp('pubWin').destroy();
}

function clearContent(){
    document.getElementById('content').innerHTML="";
}
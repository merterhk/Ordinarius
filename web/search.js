var urlpt="";
var tabPanel;
var searchCombo;
Ext.onReady(function(){

    tabPanel= new Ext.TabPanel({
        renderTo:'content',
        id:'tabPanel',
        width:800,
        height:400,
        items:[introPanel]
    });
    tabPanel.activate(introPanel);

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
            searchCombo.collapse();
            urlpt=record.data.url;
            //publicationLoad(record.data.url);
            publicationLoad(record.data.url);
        }
    });
    searchThis("Mehmet Kaya");
});

function searchThis(str){
    //    alert(str);
    searchCombo.focus();
    searchCombo.setValue(str);
    searchcombo.expand();

    searchCombo.fireEvent('keyrelease');


}
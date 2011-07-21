function publicationLoad(urlpt){

    //    var store = new Ext.data.GroupingStore({
    var store = new Ext.data.Store({
        reader: new Ext.data.JsonReader({
            root: 'pubs',
            totalProperty: 'count',
            fields: [
            'title','year','authors'
            ]
        }),
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection({
            url: '_publications.jsp?urlpt='+urlpt,
            timeout:90000
        })),
        params:{
            start:0,
            limit:25
        }
    });

    store.load({
        params:{
            start:0,
            limit:25
        }
    });

var publicationPanel=new Ext.grid.GridPanel({
    title:'Publications',
    closable:true,
    width:600,
    loadingText:'Please wait..',
    store: store,
    columns: [new Ext.grid.RowNumberer({
        width: 30
    }),

    {
        header: "Publication",
        //hidden:true,Optiği gizler
        dataIndex: 'title',
        renderer:renderTopic,
        width:.90
    }
    //        ,{
    //            header: "Authors",
    //            //hidden:true,Optiği gizler
    //            dataIndex: 'authors',
    //            width:.10
    //        },{
    //            header: "Year",
    //            //hidden:true,Optiği gizler
    //            dataIndex: 'year',
    //            width:.10
    //        }

    ],
    view: new Ext.grid.GridView({
        forceFit:true
    //groupTextTpl: '{text} ({[values.rs.length]} ders)'
    }),
    loadMask:true,
    header:false,
    tbar:[
    {
        text:'Analyze my publications',
        handler:function(){
            analyzePublications(urlpt);
        }
    },

    {
        text:'Suggest me Conference',
        handler:function(){}
    },

    {
        text:'Suggest me Person',
        handler:function(){}
    }
    ],
    bbar: new Ext.PagingToolbar({
        pageSize: 25,
        store: store,
        displayInfo: true,
        displayMsg: 'Displaying topics {0} - {1} of {2}',
        emptyMsg: "No topics to display",
        items:[
        '-', {
            pressed: true,
            enableToggle:true,
            text: 'Show Preview',
            cls: 'x-btn-text-icon details',
            toggleHandler: function(btn, pressed){
                var view = publicationPanel.getView();
                view.showPreview = pressed;
                view.refresh();
            }
        }]
    })
});

function renderTopic(value, p, record){
    return String.format(
        '<b>{0}</b><br>{1}<br>{2}</a>',
        value, record.data.year, record.data.authors);
}

//    search.enable();
tabPanel.add(publicationPanel);
tabPanel.activate(publicationPanel);

}
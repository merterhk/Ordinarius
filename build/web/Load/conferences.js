function showConferences(conf){

    var store = new Ext.data.Store({
        reader: new Ext.data.JsonReader({
            root: 'root',
            totalProperty: 'count',
            fields: [
            'title','link','description'
            ]
        }),
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection({
            url: 'Load/_conferences.jsp?conf='+conf,
            timeout:90000
        })),
        autoLoad:true
    });

    var conferencesPanel=new Ext.grid.GridPanel({
        loadingText:'Please wait..',
        store: store,
        columns: [new Ext.grid.RowNumberer({
            width: 30
        }),

        {
            header: "Title",
            //hidden:true,Optiği gizler
            dataIndex: 'title',
            renderer:renderTopic,
            width:.90
        }
        ],
        view: new Ext.grid.GridView({
            forceFit:true
        //groupTextTpl: '{text} ({[values.rs.length]} ders)'
        }),
        loadMask:true,
        hideHeaders:true
    });

    function renderTopic(value, p, record){
        return String.format(
            '<b>{0}</b><br>{2}<br><a href="{1}" target="_blank">Click here to see details.</a>',
            value,record.data.link,record.data.description);
    }


    var win = new Ext.Window({
        id:'confWin',
        title:'Conferences about '+conf,
        width:800,
        height:500,
        modal:true,
        layout:'fit',
        items:[conferencesPanel]
    }).show();

//    if(Ext.getCmp('catWin')!=null)
//        Ext.getCmp('catWin').destroy();
}


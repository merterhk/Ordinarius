function conferencesAbout(conf){

    var store = new Ext.data.Store({
        reader: new Ext.data.JsonReader({
            root: 'root',
            totalProperty: 'count',
            fields: [
            'title','link','description'
            ]
        }),
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection({
            url: '_conferences.jsp?conf='+conf,
            timeout:90000
        })),
        autoLoad:true
    });

    var conferencesPanel=new Ext.grid.GridPanel({
        title:'Conferences about '+conf,
        closable:true,
        width:600,
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
        header:false,
        tbar:[
    ]
    });

    function renderTopic(value, p, record){
        return String.format(
            '<b>{0}</b><br>{2}<br><a href="{1}">Click here to see details.</a>',
            value,record.data.link,record.data.description);
    }

    //    search.enable();
    tabPanel.add(conferencesPanel);
    tabPanel.activate(conferencesPanel);

}
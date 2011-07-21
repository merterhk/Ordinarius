function showCategories(){

    //    var store = new Ext.data.GroupingStore({
    var store = new Ext.data.Store({
        reader: new Ext.data.JsonReader({
            root: 'cats',
            totalProperty: 'count',
            fields: [
            'title'
            ]
        }),
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection({
            url: 'Data/_categories.jsp',
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
            limit:20
        }
    });

    var publicationPanel=new Ext.grid.GridPanel({
        title:'Categories',
        closable:true,
        width:600,
        loadingText:'Please wait..',
        store: store,
        hideHeaders:true,
        columns: [
        {
            header: "Category",
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
        
        bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: store,
            displayInfo: true,
            displayMsg: 'Displaying {0} - {1} of {2}',
            emptyMsg: "No publications to display",
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
            '<div style="float:left;" class="tagged"><b>{0}</b><br><a class="link" onclick="showConferences(\'{0}\');">See conferences about {0}.</a></div>\n\
            <div class="delete" style="float:right"><a class="link" onclick="hideConference(\'{0}\')">Not concerned with this.</a></div>',
            value);
    }

    //    search.enable();
    //tabPanel.add(publicationPanel);
    //tabPanel.activate(publicationPanel);
    var win = new Ext.Window({
        id:'catWin',
        title:'Categories',
        //        maximized:true,
        width:800,
        height:500,
        collapsible:true,
        layout:'fit',
        items:[publicationPanel]
    }).show();


}
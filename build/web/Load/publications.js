function showPublications(name){

    //    var store = new Ext.data.GroupingStore({
    var store = new Ext.data.Store({
        reader: new Ext.data.JsonReader({
            root: 'pubs',
            totalProperty: 'count',
            fields: [
            'title','year','authors','journal','booktitle'
            ]
        }),
        proxy: new Ext.data.HttpProxy(new Ext.data.Connection({
            url: 'Data/_publications.jsp',
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
        title:'Publications',
        closable:true,
        width:600,
        loadingText:'Please wait..',
        store: store,
        hideHeaders:true,
        columns: [
        {
            header: "Publication",
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
        var cfp =""+ record.data.journal==null ? record.data.boktitle : record.data.journal;
        return String.format(
            '<div class="tagged" style="float:left;"><b>{0}</b><br>{2}<br><a target="_blank" href="http://wikicfp.com/cfp/servlet/tool.search?q={3}&year=t">{3}</a></a></div><div style="float:right; font-size:15px;">{1}</a></div>',
            value, record.data.year, record.data.authors,cfp);
    }

    //    search.enable();
    //tabPanel.add(publicationPanel);
    //tabPanel.activate(publicationPanel);
    var win = new Ext.Window({
        id:'pubWin',
        title:'Publications ('+name+')',
        //        maximized:true,
        width:800,
        height:500,
        collapsible:true,
        layout:'fit',
        items:[publicationPanel]
    }).show();

}
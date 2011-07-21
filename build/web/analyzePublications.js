function analyzePublications(urlpt){
    var store = new Ext.data.GroupingStore({
        reader: new Ext.data.JsonReader({
            root: 'analyze',
            totalProperty: 'count',
            fields: [
            'a'
            ]
        }),
        url: '_analyze.jsp?urlpt='+urlpt,
        autoLoad:true
    });

    var analyzePanel=new Ext.grid.GridPanel({
        title:'Analyze',
        closable:true,
        width:600,
        loadingText:'Please wait..',
        store: store,
        columns: [new Ext.grid.RowNumberer({
            width: 30
        }),

        {
            header: "Subjects",
            dataIndex: 'a',
            renderer:renderTopic,
            width:.90
        }
       
        ],
        view: new Ext.grid.GroupingView({
            forceFit:true
        }),
        loadMask:true,
        header:false
    });

    function renderTopic(value, p, record){
        return String.format(
            '<div style="float:left;"><b>{0}</b><br><a class="link" onclick="conferencesAbout(\'{0}\')">See conferences about "{0}"</a></div>  <a class="link" style="float:right" onclick="alert(\'Understand. I won\\\'t show this again.\');">not concerned with it.</a>',
            value);
    }

    //    search.enable();
    tabPanel.add(analyzePanel);
    tabPanel.activate(analyzePanel);

}
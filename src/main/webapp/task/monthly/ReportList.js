Ext.define('Monthly.ReportList', {
    extend: 'Ext.grid.Panel',
    alias : 'widget.reportlist',
//    title : 'Task list',
    border : false,
	forceFit : true,
	url        : contextPath+'/task/listMonthlyTasks',
    initComponent: function() {
//    	var me = this;
    	this.selModel = Ext.create('Ext.selection.CheckboxModel');
    	this.store = Ext.create('Ext.data.Store', {
    	     autoLoad : true,
    	     proxy: {
    	         type: 'ajax',
    	         url: this.url,
    	         extraParams:{
    	        	 start : 0,
    	        	 limit : 1000
    	         },
    	         reader: {
    	             type: 'json',
    	             rootProperty: 'recordlist',
    	             totalProperty: 'total'
    	         }
    	     },
    	     groupField: 'statusDesc',
    	     groupDir : 'DESC',
    	     fields: [
    	              'id', 
    	              'taskId', 
    	              'description', 
    	              'releaseVersion',
    	              'progress',
    	              'status',
    	              'checked',
    	              'teamName',
    	              'statusDesc',
    	              'fontColor',
    	              'bgColor',
    	              'assignDate',
    	              'fixedDate',
    	              'owner',
    	              'ownerName',
    	              'commentsAndQuestions',
    	              'pendingIssues',
    	              'feedback',
    	              'submitTime',
    	              'lastUpdateTime',
    	              {
    	              	name : 'submitDate',
    	              	depends : 'submitTime',
    	              	convert : function(value, record) {
    	              		if (!value) {
    	     	         		var date = Ext.Date.parse(record.get('submitTime'), "Y-m-d H:i:s", true);
    	     	         		return Ext.Date.format(date, 'Y-m-d');
    	              		} else {
    	              			return value;
    	              		}
    	              	}
    	              }
    	         ]
    	 });
    	
    	this.columns = [ 
//    	                 {
//    	    xtype : 'checkcolumn', 
//    	    text : 'Check', 
//    	    sortable : false,
//    	    dataIndex : 'checked'
//		}, 
		{
			header : 'Task ID',
			dataIndex : 'taskId',
			width : 50
		}, {
			header : 'Description',
			dataIndex : 'description'
		}, {
			header : 'Release Version',
			dataIndex : 'releaseVersion',
			width : 70
		}, {
			header : 'Progress(%)',
			dataIndex : 'progress',
			width : 70,
			renderer : function(v){
				return v+"%";
			}
		}, {
			header : 'Status',
			dataIndex : 'statusDesc',
			renderer : function(value, metaData, record, rowIndex, colIndex, store, view) {
				var color = record.data.bgColor;
				var fcolor = record.data.fontColor;
				metaData.tdStyle += 'background:' + color + ";color:" + fcolor + ";";
				return value;
			}
		}, {
			header : 'Assigned Date',
			dataIndex : 'assignDate',
			width : 80
		}, {
			header : 'Fixed Date',
			dataIndex : 'fixedDate',
			width : 80
		}, {
			header : 'Owner',
			dataIndex : 'ownerName',
			width : 70
		}
//		, {
//			header : 'Comments/Questions',
//			dataIndex : 'commentsAndQuestions'
//		}, {
//			header : 'Pending Issues',
//			dataIndex : 'pendingIssues'
//		}, {
//			header : 'Feedback',
//			dataIndex : 'feedback'
//		}
		, {
			header : 'Submit Date',
			dataIndex : 'submitDate',
			hidden : true
		} ];
    	
    	this.features = [ {
//			id : 'group',
			ftype : 'grouping',
			groupHeaderTpl : '{groupValue}',
			hideGroupedHeader : false,
			enableGroupingMenu : false
		} ];
    	this.store.on("load", this._onLoad, this);
    	this.callParent(arguments);
    },
    _onLoad : function( store, records, successful, eOpts){
    	var arr = new Array();
    	for(var i=0; i<records.length; i++){
    		if(records[i].data.status==1){
    			arr.push(records[i]);
    		}
    	}
    	this.selModel.select(arr);
    },
    getSelections : function(){
    	this.selModel.getSelection();
    },
    getSelectedIds : function(){
    	var recs = this.selModel.getSelection();
    	var ids = "";
    	for(var i=0; i<recs.length; i++){
    		if(i>0){
    			ids+=",";
    		}
    		ids+=recs[i].data.id;
    	}
    	return ids;
    }
});
Ext.define('Monthly.GoalsList', {
    extend: 'Ext.grid.Panel',
    alias : 'widget.goalslist',
    border : false,
	forceFit : true,
	plugins: {
		pluginId : 'goalsRowEditing',
        ptype: 'cellediting',
        clicksToEdit: 1
    },
    initComponent: function() {
    	this.tbar=["<strong>My Goals</strong>","->",{
    		icon : contextPath+'/common/images/icons/newspaper_add.png',
    		text : 'Add',
    		scope : this,
    		handler : this.addRow
    	},"-",{
    		icon : contextPath+'/common/images/icons/newspaper_delete.png',
    		text : 'Delete',
    		scope : this,
    		handler : this.delRow
    	}],
    	this.store = Ext.create('Ext.data.Store', {
    	     fields: ["goal"]
    	 });
    	
    	this.columns = [ {
    	    xtype : 'rownumberer'
		}, {
			header : 'Goal Items',
			dataIndex : 'goal',
			editor: {
                xtype : 'textfield'
            }
		}];
    	
    	this.callParent(arguments);
    },
    addRow : function(btn){
    	var row = this.store.getCount();
    	this.store.insert(row, {
		    goal : ''
		});
        this.getPlugin('goalsRowEditing').startEdit(row, 1);
    },
    delRow : function(btn){
    	var res = this.getSelection();
    	this.store.remove(res);
    },
    getGoals : function(){
    	var count = this.store.getCount();
    	var arr = new Array();
    	for(var i=0; i<count; i++){
    		var g = this.store.getAt(i).data.goal;
    		if(g!=null&&g!=""){
    			arr.push(g)
    		}
    	}
    	return Ext.JSON.encode(arr);
    }
});
Ext.define('Task.view.task.SearchBar', {
	extend : 'Ext.toolbar.Toolbar',
	alias : 'widget.searchbar',
	isOwn : true,
	enableOverflow : true,
	parent : {},
	initComponent : function() {
		var me = this;
		
		var fieldTaskID = Ext.create('Ext.form.field.Text',{
			name : 'taskId',
			emptyText : 'Task ID',
			width     : 80,
			listeners : {
				specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                        me.doSearch();
                    }
                }
			}
		});
		
		var fieldTeam = Ext.create('Ext.form.field.ComboBox',{
			name : 'team',
			emptyText : 'Team',
			displayField : 'name',
			valueField : 'id',
			hiddenName : 'team',
			multiSelect : true,
			editable : false,
			store : 'Team',
			hidden : !this.isOwn,
			queryMode : 'remote',
			width     : 110,
			listeners : {
				specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                        me.doSearch();
                    }
                }
			}
		});
		
		var fieldStatus = Ext.create('Ext.form.field.ComboBox',{
			name : 'status',
			emptyText : 'Status',
			displayField : 'description',
			valueField : 'id',
			editable : false,
			multiSelect : true,
			hiddenName : 'status',
			store : 'Status',
			queryMode : 'remote',
			width     : 110,
			listConfig : {
				minWidth : 180
			},
			listeners : {
				specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                        me.doSearch();
                    }
                }
			}
		});
		
		var fieldOwner = Ext.create('Ext.form.field.Text',{
			name : 'owner',
			hidden : this.isOwn,
			emptyText : 'Owner',
			width     : 80,
			listeners : {
				specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                        me.doSearch();
                    }
                }
			}
		});
		
		var fieldStartDate = Ext.create('Ext.form.field.Date',{
			xtype : 'datefield',
			format: 'Y-m-d',
			name : 'startDate',
			anchor : '99%',
			emptyText : 'Assigned Date'	,
			hidden    : true,
			width     : 110,
			listeners : {
				specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                        me.doSearch();
                    }
                }
			}
		});
		
		var fieldEndDate = Ext.create('Ext.form.field.Date',{
			xtype : 'datefield',
			format: 'Y-m-d',
			name : 'endDate',
			anchor : '99%',
			emptyText : 'Assigned Date'	,
			hidden    : true,
			width     : 110,
			listeners : {
				specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                        me.doSearch();
                    }
                }
			}
		});
		
		var submitDate = Ext.create('Ext.form.field.Date',{
			xtype : 'datefield',
			format: 'Y-m-d',
			anchor : '99%',
			emptyText : 'Submit Date',
			width     : 110,
			listeners : {
				specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                        me.doSearch();
                    }
                }
			}
		});
		
		if(!this.isOwn){
			submitDate.setValue(new Date());
		}
		
		var addBtn = {
			text : 'Add',
			icon : contextPath+'/common/images/icons/shape_square_add.png',
			hidden: !this.isOwn,
			action : 'add'
		}
		
		var sep1 = Ext.create('Ext.toolbar.Separator',{
				xtype:'tbseparator',
				hidden: !this.isOwn
			});
		
		var exportBtn = {
			text : 'Export',
			icon : contextPath+'/common/images/icons/page_white_excel.png',
			action : 'export'	
		}
		var listMonthlyBtn = {
			text : 'Monthly Report',
			icon : contextPath+'/common/images/icons/chart_bar.png',
			hidden: !this.isOwn,
			action : 'listMonthly'	
		}
		var filterBtn = {
			text : 'Filter',
			icon : contextPath+'/common/images/icons/find.png',
			action : 'search'
		}
		var resetBtn = {
				text : 'Clear',
				icon : contextPath+'/common/images/icons/bin_empty.png',
				scope : this,
				handler : function(){
					fieldTaskID.reset();
					fieldTeam.reset();
					fieldStatus.reset();
					fieldOwner.reset();
					fieldStartDate.reset();
					fieldEndDate.reset();
					submitDate.reset();
				}
			}
		var isSended = false;
		Ext.Ajax.request({
			url: contextPath+'/email/checkSend', 
			async : false,
			success: function(response, option) {
				var obj = Ext.util.JSON.decode(response.responseText);
				isSended = obj.success;
			},
			failure: function() {
			}
		});
		
		var hideSend = false;
		if(this.isOwn&&!isSended){
			hideSend=true;
		}
		
		var send = {
				text : '<span data-qtip="Send Task '+Ext.util.Format.date(new Date(),"Y-m-d")+'">Send</span>',
				icon : contextPath+'/common/images/icons/email_go.png',
				action : 'send',
				hidden : hideSend
			}
		this.items = [addBtn,sep1,fieldTaskID,fieldOwner,fieldTeam,fieldStatus,fieldStartDate,{
			xtype : 'label',
			text  : 'to',
			hidden : true,
		},fieldEndDate,submitDate,filterBtn,'-',resetBtn,'-',exportBtn,listMonthlyBtn,send]
		
		this.getParams = function(){
			var taskId = fieldTaskID.getValue();
			var team = fieldTeam.getValue();
			var status = fieldStatus.getValue();
			var owner = fieldOwner.getValue();
			var startDate = fieldStartDate.getRawValue();
			var endDate = fieldEndDate.getRawValue();
			var subdate = submitDate.getRawValue();
			var params = {
					taskId : taskId,
					status : status,
					owner : owner,
					startDate : startDate,
					endDate : endDate,
					submitDate : subdate,
					isOwn   : this.isOwn
			};
			if(this.isOwn){
				params.team=team;
			}else{
				params.team=this.parent.getTeam();
			}
			return params;
		};
		
		this.callParent(arguments);
	},
	doSearch : function(){
		var params = this.getParams();
		this.parent.reload(params);
	},
	getParent : function(){
		return this.parent;
	},
	getOwn : function(){
		return this.isOwn;
	},
	getTeam : function(){
		return this.parent.getTeam();
	}
});

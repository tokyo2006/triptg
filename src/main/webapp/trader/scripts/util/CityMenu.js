

//var action = new Ext.Action({
//        text: 'Action 1',
//        handler: function(){
//            Ext.example.msg('Click','You clicked on "Action 1".');
//        },
//        iconCls: 'blist'
//    });
////###############################################

/**
 * 
 * 初始化类别菜单信息
 * 
 */
function initMenu(cityList)
{
	var menu = new Ext.menu.Menu({
        id: 'mainMenu'
    });
	
	var menuitem;
	var menuChilditem;
	var menuChilditemList=new Array();
		
	for(var i=0; i<cityList.length; i++)
	{		
		menuChilditemList.push('<b class="menu-title">请选择分类</b>');
		for(var j=0; j<cityList[i].children.length; j++)
		{
			menuchilditem = createMenuChild(cityList[i].children[j]);
			menuChilditemList.push(menuchilditem);
			if(j==(cityList[i].children.length-1))
			{
				menuitem = createchildMenuList(menuChilditemList,cityList[i]);
				menu.add(menuitem);
				menuChilditemList.length=0;
			}
		}
	}
	
	//状态栏（包含菜单）
    var tb = new Ext.Toolbar();
    tb.render('toolbar');

    tb.add({
            text:'城市类别管理',
            iconCls: 'bmenu',  // <-- icon
            frame:true,
            menu: menu  // assign menu by instance
//            menu: [action]
        }
        );
}



function createMenuChild(menuchilditem)
{
	var menuchilditemtemp = {
		id:menuchilditem.areaId,
        text: menuchilditem.name,
		stateId:menuchilditem.flag,
        handler: onItemClick
    }
	return menuchilditemtemp;
}

function createchildMenuList(menuChilditemList,cityList)
{
	menuChilditemListtemp = {
		text: cityList.name,
		menu: {        // <-- submenu by nested config object
        		items: menuChilditemList
              }
	}
	return menuChilditemListtemp;
}
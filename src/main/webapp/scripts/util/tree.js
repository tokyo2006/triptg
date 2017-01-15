/**  
 
 * @class Ext.tree.CheckboxTree  
 
 * 含有checkbox的树状菜单  
 
 * @param {Object} config中的参数  
 
 * el                   必输入  
 
 * dataUrl              必输入  
 
 * rootText             必输入  
 
 * rootId               默认值为0  
 
 */  
  
Ext.tree.CheckboxTree=function(config){    
  
       
  
    var myTreeLoader=new Ext.tree.TreeLoader({   
  
            dataUrl:config.dataUrl   
  
    });   
  
       
  
    myTreeLoader.on("beforeload", function(treeLoader, node) {   
  
        treeLoader.baseParams.check = node.attributes.checked;   
  
    }, this);   
  
       
  
    Ext.tree.CheckboxTree.superclass.constructor.call(this,{   
  
        animate:true,   
  
        enableDD:true,   
  
        autoScroll:true,   
  
        useArrows:true,   
  
        containerScroll:true,   
  
        dropConfig: {appendOnly:true},   
  
        el:config.el,   
  
        root:new Ext.tree.AsyncTreeNode({   
  
            text:config.rootText,   
  
            draggable:false,   
  
            checked:true,   
  
            id:'0'||config.rootId   
  
        }),   
  
        loader:myTreeLoader   
  
    });   
  
       
  
    new Ext.tree.TreeSorter(this,{folderSort:true});   
  
       
  
    this.on({   
  
        'checkchange':function(node,checked){   
  
  
  
            var parentNode=node.parentNode;   
  
                   
  
            if(checked){   
  
                /**  
 
                 * 节点为真时，此节点的子节点,判断此节点的父节点时，判断父节点的子节点是否全部为  
 
                 * 真，如果全部为真，则此父节点为真，如果不为真则不变 全部为真  
 
                 */  
  
                var childNodes=node.childNodes;   
  
                   
  
                for(var i=0;i<childNodes.length;i++){   
  
                    var childNode=childNodes[i];   
  
                    if(!childNode.attributes.checked){   
  
                        childNode.ui.toggleCheck();   
  
                    }   
  
                }   
  
                /**  
 
                 * 此如果此节点又父节点，则判断此父节点的子节点是否全为真 如果全为真则此父节点也为真  
 
                 */        
  
                if(parentNode&&!parentNode.attributes.checked){   
  
                    var nodes=parentNode.childNodes   
  
                    for(var i=0;i<nodes.length;i++){   
  
                        /**  
 
                         * 当一个父节点的子节点中的一个节点为false是，此复节点应为false  
 
                         */  
  
                        if(!nodes[i].attributes.checked){      
  
                            /**  
 
                             * 如果此父节点为真时，改变父节点的状态  
 
                             */  
  
                            if(parentNode.attributes.checked){   
  
                                parentNode.attributes.checked=false;   
  
                                parentNode.ui.toggleCheck();   
  
                            }   
  
                            return ;   
  
                        }   
  
                    }   
  
                    parentNode.attributes.checked=true;   
  
                    parentNode.ui.toggleCheck();   
  
                }   
  
            }else{   
  
                /**  
 
                 * 如果为false时，  
 
                 */  
  
                if(parentNode&&parentNode.attributes.checked){   
  
                    parentNode.attributes.checked=false;   
  
                    parentNode.ui.toggleCheck();   
  
                }   
  
            }   
  
           
  
        }   
  
    });      
  
}   
  
  
  
Ext.extend(Ext.tree.CheckboxTree, Ext.tree.TreePanel,{   
  
//    /**  
// 
//     * 展开根节点  
// 
//     * @return          返回根节点  
// 
//     */  
//  
//    expandRoot:function(){   
//  
//        this.root.expand();   
//  
//        return this.root;   
//<IMG height=21 alt="" src="skins/Default/toolbar/universalkey.gif" width=21>   
//    }   
  
});   
Ext.reg('checkboxtree', Ext.tree.CheckboxTree);
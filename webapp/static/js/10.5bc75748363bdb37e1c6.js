webpackJsonp([10],{HMPg:function(n,e,t){var a=t("zXzt");"string"==typeof a&&(a=[[n.i,a,""]]),a.locals&&(n.exports=a.locals);t("5ten")("05b6ae8e",a,!0,{})},Myjh:function(n,e,t){"use strict";function a(n){t("HMPg")}Object.defineProperty(e,"__esModule",{value:!0});var i=t("aA9S"),r=t.n(i),s={data:function(){return{addBrand:!1,editorWindow:!1,department:"",listLoading:!1,editLoading:!1,brandList:[],imageUrl:"",form:{id:"",name:"",price:"",brandId:"",image:""},searchForm:{name:"",beginPrice:"",endPrice:"",brand:""},tableData:[],currentPage:1,total:""}},mounted:function(){this.brandRequest(),this.rqList()},methods:{rqList:function(){var n=this;this.$fetch("/system/findPhone",{page:1,rows:10}).then(function(e){e?(n.total=e.total,n.tableData=e.rows,n.listLoading=!1):n.$message({message:e.msg,type:"warning"})})},brandRequest:function(){var n=this;this.$fetch("/system/findBrand",{page:1,rows:10}).then(function(e){200===e.status?n.brandList=e.rows:n.$message({message:e.msg,type:"warning"})})},searchSub:function(){var n=this;this.editLoading=!0,this.listLoading=!0,this.$fetch("/system/seachPhoneByOwn",{name:this.searchForm.name,beginPrice:this.searchForm.beginPrice,endPrice:this.searchForm.endPrice,brand:this.searchForm.brand,page:1,rows:10}).then(function(e){e?(n.listLoading=!1,n.editLoading=!1,n.tableData=e.rows):n.$message({message:e.msg,type:"warning"})})},newBrand:function(){this.addBrand=!0},beforeAvatarUpload:function(n){var e=n.size/1024/1024<.5;return e||this.$message.error("上传模板大小不能超过 2MB!"),e},getFile:function(n){var e=this,t=document.getElementById("test"),a=t.value;console.log(a),this.$fetch("/imageUpload",this.result).then(function(n){200===n.status?e.$message({message:"添加成功！",type:"success"}):e.$message({message:n.msg,type:"warning"})})},addSeave:function(){var n=this;this.$message({message:"添加成功！",type:"success"});var e={name:this.form.name,price:this.form.price,brand:this.form.brand};this.$post("/system/savePhone",e).then(function(e){200===e.status?(n.rqList(),n.$message({message:"添加成功！",type:"success"})):n.$message({message:e.msg,type:"warning"})})},handleSizeChange:function(n){console.log("每页 "+n+" 条")},handleCurrentChange:function(n){console.log("当前页: "+n)},editor:function(n,e){this.editorWindow=!0,this.form=r()({},e),this.table_index=n},handleDelete:function(n,e){var t=this;this.$confirm("确认删除","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.editLoading=!0,t.$fetch("/system/deletePhone",{id:e.id}).then(function(e){200===e.status?(t.editLoading=!1,t.tableData.splice(n,1),t.$message({message:"操作成功！",type:"success"})):t.$message({message:e.msg,type:"warning"})})}).catch(function(){t.$message({type:"info",message:"已取消删除"})})},handleSave:function(){var n=this,e=new FormData;e.append("file",this.file);this.form.name,this.form.price,this.form.brand;this.$confirm("确认提交吗？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",cancelButtonClass:"cancel"}).then(function(){n.editLoading=!0,n.tableData.splice(n.table_index,1,n.form),n.editLoading=!1,n.$message({message:"操作成功！",type:"success"}),n.rqList(),n.editorWindow=!1,n.resetStu()}).catch(function(){})},cancel:function(){this.addBrand=!1,this.editorWindow=!1,this.resetStu()},resetStu:function(){this.form={id:"",name:"",price:"",brand:"",image:""},this.imageUrl=""}}},o=function(){var n=this,e=n.$createElement,t=n._self._c||e;return t("section",{staticClass:"overh"},[t("div",{staticClass:"warp-breadcrum warp-top"},[t("el-breadcrumb",{attrs:{"separator-class":"el-icon-arrow-right"}},[t("el-breadcrumb-item",{attrs:{to:{path:"/manage"}}},[n._v("首页")]),n._v(" "),n._l(this.$route.matched,function(e){return t("el-breadcrumb-item",{key:e.index},[n._v(n._s(e.name))])})],2)],1),n._v(" "),t("div",{staticClass:"warp-operation warp-top"},[t("el-button",{attrs:{type:"primary",size:"medium",icon:"el-icon-circle-plus-outline"},on:{click:n.newBrand}},[n._v("新 增")])],1),n._v(" "),t("div",{staticClass:"warp-operation warp-top"},[t("el-form",{attrs:{inline:!0,model:n.searchForm}},[t("el-form-item",{attrs:{label:"商品名称"}},[t("el-input",{attrs:{placeholder:"请输入商品名称"},model:{value:n.searchForm.name,callback:function(e){n.$set(n.searchForm,"name",e)},expression:"searchForm.name"}})],1),n._v(" "),t("el-form-item",{attrs:{label:"品牌名称"}},[t("el-select",{attrs:{clearable:"",placeholder:"请选择"},model:{value:n.searchForm.brand,callback:function(e){n.$set(n.searchForm,"brand",e)},expression:"searchForm.brand"}},n._l(n.brandList,function(n){return t("el-option",{key:n.index,attrs:{label:n.brand,value:n.id}})}))],1),n._v(" "),t("el-form-item",{attrs:{label:"价格区间"}},[t("el-col",{attrs:{span:11}},[t("el-form-item",[t("el-input",{attrs:{placeholder:"最低价格(元)"},model:{value:n.searchForm.beginPrice,callback:function(e){n.$set(n.searchForm,"beginPrice",e)},expression:"searchForm.beginPrice"}})],1)],1),n._v(" "),t("el-col",{attrs:{span:1}},[n._v("-")]),n._v(" "),t("el-col",{attrs:{span:12}},[t("el-form-item",[t("el-input",{attrs:{placeholder:"最高价格(元)"},model:{value:n.searchForm.endPrice,callback:function(e){n.$set(n.searchForm,"endPrice",e)},expression:"searchForm.endPrice"}})],1)],1)],1),n._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary",size:"medium",icon:"el-icon-search",loading:n.editLoading},on:{click:n.searchSub}},[n._v("搜 索")])],1)],1)],1),n._v(" "),t("el-col",{staticClass:"warp-container"},[t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:n.listLoading,expression:"listLoading"}],ref:"multipleTable",staticClass:"q vhz-table",staticStyle:{width:"100%"},attrs:{data:n.tableData,"tooltip-effect":"dark"}},[t("el-table-column",{attrs:{prop:"id",label:"ID",width:"70"}}),n._v(" "),t("el-table-column",{attrs:{prop:"name",label:"商品名称",width:"160"}}),n._v(" "),t("el-table-column",{attrs:{prop:"price",label:"商品价格",width:"160"}}),n._v(" "),t("el-table-column",{attrs:{prop:"brandId",label:"品牌名称",width:"160"}}),n._v(" "),t("el-table-column",{attrs:{prop:"image",label:"图片",width:"160"},scopedSlots:n._u([{key:"default",fn:function(n){return[t("img",{staticStyle:{width:"50px",height:"50px"},attrs:{src:n.row.image,alt:""}})]}}])}),n._v(" "),t("el-table-column",{attrs:{prop:"tag",label:"操 作","filter-placement":"bottom-end"},scopedSlots:n._u([{key:"default",fn:function(e){return[t("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){n.editor(e.$index,e.row)}}},[n._v("编 辑")]),n._v(" "),t("el-button",{attrs:{type:"danger",size:"mini",loading:n.editLoading},on:{click:function(t){n.handleDelete(e.$index,e.row)}}},[n._v("删 除")])]}}])})],1),n._v(" "),t("div",{staticClass:"block"},[t("el-pagination",{attrs:{"current-page":n.currentPage,"page-sizes":[10,20,30,40],"page-size":2,layout:"total, sizes, prev, pager, next, jumper",total:n.total},on:{"size-change":n.handleSizeChange,"current-change":n.handleCurrentChange}})],1)],1),n._v(" "),t("el-dialog",{attrs:{title:"新 增",visible:n.addBrand,width:"30%"},on:{"update:visible":function(e){n.addBrand=e}}},[t("el-form",{ref:"addForm",attrs:{model:n.form,"label-width":"80px"}},[t("el-form-item",{attrs:{label:"商品名称"}},[t("el-input",{model:{value:n.form.name,callback:function(e){n.$set(n.form,"name",e)},expression:"form.name"}})],1),n._v(" "),t("el-form-item",{attrs:{label:"商品价格"}},[t("el-input",{model:{value:n.form.price,callback:function(e){n.$set(n.form,"price",e)},expression:"form.price"}})],1),n._v(" "),t("el-form-item",{attrs:{label:"品牌名称"}},[t("el-select",{attrs:{clearable:"",placeholder:"请选择"},model:{value:n.form.id,callback:function(e){n.$set(n.form,"id",e)},expression:"form.id"}},n._l(n.brandList,function(n){return t("el-option",{key:n.index,attrs:{label:n.brand,value:n.id}})}))],1),n._v(" "),t("el-form-item",{attrs:{label:"图 片"}},[t("div",{staticClass:"avatar-uploader"},[t("div",{staticClass:"input-file"},[t("input",{ref:"file",attrs:{type:"file",id:"test",size:"1",accept:"image/gif,image/jpeg,image/jpg,image/png"},on:{change:n.getFile}}),n._v(" "),t("i",{staticClass:"el-icon-plus avatar-uploader-icon"}),n._v(" "),t("img",{attrs:{src:n.imageUrl,alt:""}})])]),n._v(" "),t("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[n._v("请上传png、jpg及jpeg格式图片(大小2M以下)")])])],1),n._v(" "),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:n.cancel}},[n._v("取 消")]),n._v(" "),t("el-button",{attrs:{type:"primary",loading:n.editLoading},on:{click:n.addSeave}},[n._v("确 定")])],1)],1),n._v(" "),t("el-dialog",{attrs:{title:"编 辑",visible:n.editorWindow,size:"tiny"},on:{"update:visible":function(e){n.editorWindow=e}}},[t("el-form",{ref:"editorForm",attrs:{model:n.form,"label-width":"80px"}},[t("el-form-item",{attrs:{label:"商品名称"}},[t("el-input",{model:{value:n.form.name,callback:function(e){n.$set(n.form,"name",e)},expression:"form.name"}})],1),n._v(" "),t("el-form-item",{attrs:{label:"商品价格"}},[t("el-input",{model:{value:n.form.price,callback:function(e){n.$set(n.form,"price",e)},expression:"form.price"}})],1),n._v(" "),t("el-form-item",{attrs:{label:"品牌名称"}},[t("el-select",{attrs:{clearable:"",placeholder:"请选择"},model:{value:n.form.id,callback:function(e){n.$set(n.form,"id",e)},expression:"form.id"}},n._l(n.brandList,function(n){return t("el-option",{key:n.index,attrs:{label:n.brand,value:n.id}})}))],1),n._v(" "),t("el-form-item",[t("el-input",{attrs:{type:"hidden"},model:{value:n.form.id,callback:function(e){n.$set(n.form,"id",e)},expression:"form.id"}})],1),n._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary",loading:n.editLoading},on:{click:function(e){n.handleSave()}}},[n._v("修改")]),n._v(" "),t("el-button",{on:{click:n.cancel}},[n._v("取消")])],1)],1)],1)],1)},l=[],c={render:o,staticRenderFns:l},d=c,m=t("X4nt"),u=a,p=m(s,d,!1,u,null,null);e.default=p.exports},zXzt:function(n,e,t){e=n.exports=t("I71c")(!0),e.push([n.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n","",{version:3,sources:[],names:[],mappings:"",file:"goods.vue",sourceRoot:""}])}});
//# sourceMappingURL=10.5bc75748363bdb37e1c6.js.map
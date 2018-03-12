<template>
  <section class="overh">
    <div class="warp-operation warp-top">
      <el-button type="primary" size="medium" icon="el-icon-circle-plus-outline" @click="newBrand">新 增</el-button>
    </div>
    <div class="warp-operation warp-top">
      <el-form :inline="true" :model="searchForm">
        <el-form-item label="商品名称">
          <el-input v-model="searchForm.name" placeholder="请输入商品名称"></el-input>
        </el-form-item>
        <el-form-item label="品牌名称">
          <el-select v-model="searchForm.brand" clearable placeholder="请选择">
            <el-option
              v-for="item in brandList"
              :key="item.index"
              :label="item.brand"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="价格区间">
          <el-col :span="11">
            <el-form-item>
              <el-input v-model="searchForm.beginPrice" placeholder="最低价格(元)"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="1">-</el-col>
          <el-col :span="12">
            <el-form-item>
              <el-input v-model="searchForm.endPrice" placeholder="最高价格(元)"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="medium" icon="el-icon-search" @click="searchSub" :loading="editLoading">搜 索</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-col class="warp-container">
      <el-table ref="multipleTable" class="q vhz-table" v-loading="listLoading" :data="tableData" tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="id" label="ID" width="70"></el-table-column>
        <el-table-column prop="name" label="商品名称" width="160"></el-table-column>
        <el-table-column prop="price" label="商品价格" width="160"></el-table-column>
        <el-table-column prop="brandId" label="品牌名称" width="160"></el-table-column>
        <el-table-column prop="image" label="图片" width="160">
          <template slot-scope="scope">
            <img :src="scope.row.image" alt="" style="width: 50px;height: 50px">
          </template>
        </el-table-column>
        <el-table-column prop="tag" label="操 作" filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="editor(scope.$index, scope.row)">编 辑</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)" :loading="editLoading">删 除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[10, 20, 30, 40]"
          :page-size="2"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </el-col>
    <el-dialog
      title="新 增"
      :visible.sync="addBrand"
      width="30%">
      <el-form ref="addForm" :model="form" label-width="80px">
        <el-form-item label="商品名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="商品价格">
          <el-input v-model="form.price"></el-input>
        </el-form-item>
        <el-form-item label="品牌名称">
          <el-select v-model="form.id" clearable placeholder="请选择">
            <el-option
              v-for="item in brandList"
              :key="item.index"
              :label="item.brand"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="图 片">
          <div class="avatar-uploader">
            <div class="input-file">
              <input type="file" ref="file" id="test" @change="getFile" size="1" accept = 'image/gif,image/jpeg,image/jpg,image/png'>
              <i class="el-icon-plus avatar-uploader-icon"></i>
              <img :src="imageUrl" alt="">
            </div>
          </div>
          <div slot="tip" class="el-upload__tip">请上传png、jpg及jpeg格式图片(大小2M以下)</div>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="addSeave" :loading="editLoading">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="编 辑" :visible.sync="editorWindow" size="tiny">
      <el-form ref="editorForm" :model="form" label-width="80px">
        <el-form-item label="商品名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="商品价格">
          <el-input v-model="form.price"></el-input>
        </el-form-item>
        <el-form-item label="品牌名称">
          <el-select v-model="form.id" clearable placeholder="请选择">
            <el-option
              v-for="item in brandList"
              :key="item.index"
              :label="item.brand"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-input type="hidden" v-model="form.id"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSave()" :loading="editLoading">修改</el-button>
          <el-button @click="cancel">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </section>
</template>

<script>
  export default {
    data () {
      return {
        addBrand: false,
        editorWindow: false,
        department: '',
        listLoading: false,
        editLoading: false,
        brandList: [],
        imageUrl: '',
        form: {
          id: '',
          name: '',
          price: '',
          brandId: '',
          image: ''
        },
        searchForm: {
          name: '',
          beginPrice: '',
          endPrice: '',
          brand: ''
        },
        tableData: [],
        currentPage: 1,
        total: ''
      }
    },
    mounted () {
      this.brandRequest()
      this.rqList()
    },
    methods: {
      rqList () { // 视图渲染
        this.$fetch('/system/findPhone', {page: 1, rows: 10})
          .then(res => {
            if (res) {
              this.total = res.total
              this.tableData = res.rows
              this.listLoading = false
            } else {
              this.$message({
                message: res.msg,
                type: 'warning'
              })
            }
          })
      },
      brandRequest () {
        this.$fetch('/system/findBrand', {page: 1, rows: 10})
          .then(res => {
            if (res.status === 200) {
              this.brandList = res.rows
            } else {
              this.$message({
                message: res.msg,
                type: 'warning'
              })
            }
          })
      },
      searchSub () { // 搜 索
        this.editLoading = true
        this.listLoading = true
        this.$fetch('/system/seachPhoneByOwn', {
          name: this.searchForm.name,
          beginPrice: this.searchForm.beginPrice,
          endPrice: this.searchForm.endPrice,
          brand: this.searchForm.brand,
          page: 1,
          rows: 10
        })
          .then(res => {
            if (res) {
              this.listLoading = false
              this.editLoading = false
              this.tableData = res.rows
            } else {
              this.$message({
                message: res.msg,
                type: 'warning'
              })
            }
          })
      },
      newBrand () { // 新增按钮
        this.addBrand = true
      },
      beforeAvatarUpload (file) { // 上传图片前的
        const isLt2M = file.size / 1024 / 1024 < 0.5
        if (!isLt2M) {
          this.$message.error('上传模板大小不能超过 2MB!')
        }
        return isLt2M
      },
      getFile (node) {
        var img = document.getElementById('test')
        var path = img.value
        console.log(path)
        this.$fetch('/imageUpload', this.result)
          .then(res => {
            if (res.status === 200) {
              this.$message({
                message: '添加成功！',
                type: 'success'
              })
            } else {
              this.$message({
                message: res.msg,
                type: 'warning'
              })
            }
          })
      },
      addSeave () {
//        this.rqList()
        this.$message({
          message: '添加成功！',
          type: 'success'
        })
        let parameter = {
          name: this.form.name,
          price: this.form.price,
          brand: this.form.brand
        }
        this.$post('/system/savePhone', parameter)
          .then(res => {
            if (res.status === 200) {
              this.rqList()
              this.$message({
                message: '添加成功！',
                type: 'success'
              })
            } else {
              this.$message({
                message: res.msg,
                type: 'warning'
              })
            }
          })
      },
      handleSizeChange (val) {
        console.log(`每页 ${val} 条`)
      },
      handleCurrentChange (val) {
        console.log(`当前页: ${val}`)
      },
      editor (index, row) {
        this.editorWindow = true
        this.form = Object.assign({}, row)
        this.table_index = index
      },
      handleDelete (index, row) {
        this.$confirm('确认删除', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.editLoading = true
          this.$fetch('/system/deletePhone', {id: row.id})
            .then((res) => {
              if (res.status === 200) {
                this.editLoading = false
                this.tableData.splice(index, 1)
                this.$message({
                  message: '操作成功！',
                  type: 'success'
                })
              } else {
                this.$message({
                  message: res.msg,
                  type: 'warning'
                })
              }
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      handleSave () { // 修改
        var fd = new FormData()
        fd.append('file', this.file)
        var parameter = {
          name: this.form.name,
          price: this.form.price,
          brand: this.form.brand,
          image: fd
        }
        this.$confirm('确认提交吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          cancelButtonClass: 'cancel'
        }).then(() => {
          this.editLoading = true
          this.tableData.splice(this.table_index, 1, this.form)
          this.editLoading = false
          this.$message({
            message: '操作成功！',
            type: 'success'
          })
          this.rqList()
          this.editorWindow = false
          this.resetStu()
//          this.$post('/system/updatePhone', parameter)
//            .then((res) => {
//              this.tableData.splice(this.table_index, 1, this.form)
//              this.editLoading = false
//              this.$message({
//                message: '操作成功！',
//                type: 'success'
//              })
//              this.rqList()
//              this.editorWindow = false
//              this.resetStu()
//            })
        }).catch(() => {

        })
      },
      cancel () { // 取消 重置表单
        this.addBrand = false
        this.editorWindow = false
        this.resetStu()
      },
      resetStu: function () {
        this.form = {
          id: '',
          name: '',
          price: '',
          brand: '',
          image: ''
        }
        this.imageUrl = ''
      }
    }
  }
</script>
<style>
</style>

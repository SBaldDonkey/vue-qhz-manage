<template>
  <section class="overh">
    <div class="warp-operation warp-top">
      <el-button type="primary" size="medium" icon="el-icon-circle-plus-outline" @click="addBrand = true">新 增</el-button>
    </div>
    <div class="warp-operation warp-top">
      <el-form :inline="true" :model="searchForm" ref="searchForm">
        <el-form-item label="品牌名称">
          <el-input v-model="searchForm.brandName" placeholder="请输入品牌名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="medium" icon="el-icon-search" @click="searchSub">搜 索</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-col class="warp-container">
      <el-table ref="multipleTable" class="q vhz-table" v-loading="listLoading" :data="tableData" tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="id" label="品牌ID" width="100">
        </el-table-column>
        <el-table-column prop="brand" label="品牌名称" width="200"> </el-table-column>
        <el-table-column prop="tag" label="操 作" filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="editor(scope.$index, scope.row)">编 辑</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)" :loading="editLoading">删 除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
    <el-dialog
      title="新 增"
      :visible.sync="addBrand"
      width="30%"
      :before-close="closeDialog">
      <el-form ref="addForm" :model="form" label-width="80px">
        <el-form-item label="品牌名称">
          <el-input v-model="form.brand"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="addSeave()" :loading="editLoading">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="编 辑" :visible.sync="editorWindow" size="tiny" :before-close="closeDialog">
      <el-form ref="editorForm" :model="form" label-width="80px">
        <el-form-item label="品牌名称">
          <el-input v-model="form.brand"></el-input>
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
        listLoading: true,
        editLoading: false,
        brandName: '',
        searchForm: {
          brandName: ''
        },
        form: {
          brand: '',
          id: ''
        },
        tableData: []
      }
    },
    mounted () {
      this.rqList()
    },
    methods: {
      rqList () { // 视图渲染
        this.$fetch('/system/findBrand', {page: 1, rows: 10})
          .then(res => {
            if (res) {
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
      searchSub () {
        this.listLoading = true
        this.$fetch('/system/searchBrandByOwn', {brand: this.searchForm.brandName, page: 1, rows: 30})
          .then((res) => {
            if (res) {
              this.tableData = res.rows
              this.listLoading = false
            }
          })
      },
      cancel () { // 取消 重置表单
        this.addBrand = false
        this.editorWindow = false
        this.resetStu()
      },
      editor (index, row) { // 编辑按钮事件
        this.editorWindow = true
        this.form = Object.assign({}, row)
        this.table_index = index
      },
      handleDelete (index, row) { // 删除
        this.$confirm('确认删除', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.editLoading = true
          this.$fetch('/system/deleteBrand', {id: row.id})
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
      addSeave () { // 新增
        if (this.form.brand === '') {
          this.$message({
            message: '品牌名称不能为空！',
            type: 'warning'
          })
        } else {
          this.editLoading = true
          let add = {
            'brand': this.form.brand
          }
          this.$fetch('/system/saveBrand', {brand: this.form.brand})
            .then((res) => {
              if (res.status === 200) {
                this.tableData.push(add)
                this.editLoading = false
                this.rqList()
                this.addBrand = false
                this.$message({
                  message: '操作成功！',
                  type: 'success'
                })
                this.resetStu()
              } else {
                this.$message({
                  message: res.msg,
                  type: 'warning'
                })
              }
            })
        }
      },
      handleSave () { // 修改
        if (this.form.name === '') {
          this.$message({
            message: '品牌名称不能为空！',
            type: 'warning'
          })
        } else {
          this.$confirm('确认提交吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            cancelButtonClass: 'cancel'
          }).then(() => {
            this.editLoading = true
            this.$fetch('/system/updateBrand', {brand: this.form.brand, id: this.form.id})
              .then((res) => {
                if (res.status === 200) {
                  this.tableData.splice(this.table_index, 1, this.form)
                  this.editLoading = false
                  this.$message({
                    message: '操作成功！',
                    type: 'success'
                  })
                  this.rqList()
                  this.editorWindow = false
                  this.resetStu()
                } else {
                  this.$message({
                    message: res.msg,
                    type: 'warning'
                  })
                }
              })
          }).catch(() => {

          })
        }
      },
      resetStu: function () {
        this.form = {
          brand: '',
          id: ''
        }
      },
      closeDialog (don) {
        this.resetStu()
        don()
      }
    }
  }
</script>
<style>
</style>

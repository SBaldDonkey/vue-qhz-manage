<template>
  <section class="overh">
    <div class="warp-operation warp-top">
      <el-button type="primary" size="medium" icon="el-icon-circle-plus-outline" @click="dialogVisible = true">新 增</el-button>
    </div>
    <el-col class="warp-container">
      <el-table ref="multipleTable" class="qhz-table" v-loading="listLoading" :data="tableData" tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="sjId" label="ID" width="60">
        </el-table-column>
        <el-table-column prop="sjName" label="职位名称" width="120"> </el-table-column>
        <el-table-column prop="sjShortName" label="职位简称" show-overflow-tooltip> </el-table-column>
        <el-table-column prop="tag" width="200" label="" filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="editor(scope.$index, scope.row)">修 改</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)">删 除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
    <el-dialog title="新 增" :visible.sync="dialogVisible" size="tiny" :before-close="closeDialog">
      <el-form ref="editorForm" :model="form" label-width="80px">
        <el-form-item label="职位名称">
          <el-input v-model="form.sjName"></el-input>
        </el-form-item>
        <el-form-item label="职位简称">
          <el-input v-model="form.sjShortName"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addSeave()" :loading="editLoading">新 增</el-button>
          <el-button @click="cancel">取 消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="编 辑" :visible.sync="dialogFormVisible" size="tiny" :before-close="closeDialog">
      <el-form ref="editorForm" :model="form" label-width="80px">
        <el-form-item label="职位名称">
          <el-input v-model="form.sjName"></el-input>
        </el-form-item>
        <el-form-item label="职位简称">
          <el-input v-model="form.sjShortName"></el-input>
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
        dialogVisible: false,
        department: '',
        editLoading: false,
        listLoading: true,
        dialogFormVisible: false,
        form: {
          sjId: '',
          sjName: '',
          sjShortName: ''
        },
        tableData: [
          {
            sjId: '111',
            sjName: '111',
            sjShortName: '1111'
          }
        ]
      }
    },
    mounted () {
      this.resetStu()
      this.rqList()
    },
    methods: {
      rqList () {
        this.$fetch('/permissions/position', {page: 1, rows: 30})
          .then((res) => {
            this.listLoading = false
            this.tableData = res.rows
          })
      },
      editor (index, row) { // 编辑按钮事件
        this.dialogFormVisible = true
        this.form = Object.assign({}, row)
        this.table_index = index
      },
      cancel () {
        this.dialogVisible = false
        this.dialogFormVisible = false
        this.resetStu()
      },
      addSeave () {
        if (this.form.sjName === '') {
          this.$message({
            message: '职位名称不能为空！',
            type: 'warning'
          })
        } else if (this.form.sjShortName === '') {
          this.$message({
            message: '职位简称不能为空！',
            type: 'warning'
          })
        } else {
          this.editLoading = true
          this.$fetch('/permissions/position/save', {sjName: this.form.sjName, sjShortName: this.form.sjShortName})
            .then(res => {
              if (res.status === 200) {
                this.editLoading = false
                this.dialogFormVisible = false
                this.rqList()
                this.$message({
                  message: '角色添加成功！',
                  type: 'success'
                })
                this.editLoading = false
                this.dialogVisible = false
              } else {
                this.$message({
                  message: '角色添加失败！',
                  type: 'warning'
                })
                this.rqList()
              }
            })
          this.resetStu()
        }
      }, // 添加
      handleSave () {
        if (this.form.sjName === '') {
          this.$message({
            message: '职位名称不能为空！',
            type: 'warning'
          })
        } else if (this.form.sjShortName === '') {
          this.$message({
            message: '职位简称不能为空！',
            type: 'warning'
          })
        } else {
          this.$confirm('确认提交吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            cancelButtonClass: 'cancel'
          }).then(() => {
            this.editLoading = true
            this.listLoading = true
            this.$fetch('/permissions/position/update', {sjId: this.form.sjId, sjName: this.form.sjName, sjShortName: this.form.sjShortName})
              .then(res => {
                if (res.status === 200) {
                  this.rqList()
                  this.listLoading = false
                  this.editLoading = false
                  this.dialogFormVisible = false
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
            this.resetStu()
          }).catch(() => {

          })
        }
      }, // 修改
      handleDelete (index, row) {
        this.$confirm('确认删除', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$fetch('/permissions/position/delete', {sjId: row.sjId})
            .then(res => {
              if (res.status === 200) {
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
      }, // 删除
      resetStu () {
        this.form = {
          sjId: '',
          sjName: '',
          sjShortName: ''
        }
      }, // 重置表单
      closeDialog (don) {
        this.resetStu()
        don()
      }
    }
  }
</script>
<style>
</style>

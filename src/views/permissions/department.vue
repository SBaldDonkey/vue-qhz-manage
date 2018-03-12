<template>
  <section class="overh">
    <div class="warp-operation warp-top">
      <el-button type="primary" size="medium" icon="el-icon-circle-plus-outline" @click="dialogVisible = true">新 增</el-button>
    </div>
    <el-col class="warp-container">
      <el-table ref="multipleTable" class="qhz-table" v-loading="listLoading" :data="tableData" tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="sdId" label="I D" width="60">
        </el-table-column>
        <el-table-column prop="sdName" label="部 门" width="120"> </el-table-column>
        <el-table-column prop="sdShortName" label="部门简称" show-overflow-tooltip> </el-table-column>
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
        <el-form-item label="部 门">
          <el-input v-model="form.sdName"></el-input>
        </el-form-item>
        <el-form-item label="部门简称">
          <el-input v-model="form.sdShortName"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addSeave()" :loading="editLoading">新 增</el-button>
          <el-button @click="cancel">取 消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="编 辑" :visible.sync="dialogFormVisible" size="tiny" :before-close="closeDialog">
      <el-form ref="editorForm" :model="form" label-width="80px">
        <el-form-item label="部 门">
          <el-input v-model="form.sdName"></el-input>
        </el-form-item>
        <el-form-item label="部门简称">
          <el-input v-model="form.sdShortName"></el-input>
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
        listLoading: true,
        editLoading: false,
        tableData: [
          {
            sdId: '11',
            sdName: '11',
            sdShortName: '111'
          }
        ],
        form: {
          sdId: '',
          sdName: '',
          sdShortName: ''
        }
      }
    },
    mounted () {
      this.resetStu()
      this.rqList()
    },
    methods: {
      rqList () {
        this.$fetch('/permissions/department', {page: 1, rows: 30})
          .then(res => {
            if (res) {
              this.tableData = res.rows
              this.listLoading = false
            }
          })
      },
      editor (index, row) { // 编辑按钮事件
        this.dialogFormVisible = true
        this.form = Object.assign({}, row)
        this.table_index = index
      },
      handleSave () {
        if (this.form.sdName === '') {
          this.$message({
            message: '职位名称不能为空！',
            type: 'warning'
          })
        } else if (this.form.sdShortName === '') {
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
            this.$fetch('/permissions/department/update', {sdId: this.form.sdId, sdName: this.form.sdName, sdShortName: this.form.sdShortName})
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
      addSeave () {
        if (this.form.sdName === '') {
          this.$message({
            message: '职位名称不能为空！',
            type: 'warning'
          })
        } else if (this.form.sdShortName === '') {
          this.$message({
            message: '职位简称不能为空！',
            type: 'warning'
          })
        } else {
          this.editLoading = true
          this.$fetch('/permissions/department/save', {sdName: this.form.sdName, sdShortName: this.form.sdShortName})
            .then(res => {
              if (res.status === 200) {
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
      handleDelete (index, row) {
        this.$confirm('确认删除', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$fetch('/permissions/department/delete', {sdId: row.sdId})
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
      },
      cancel () {
        this.dialogVisible = false
        this.dialogFormVisible = false
        this.resetStu()
      } // 取消
    }
  }
</script>
<style>
</style>

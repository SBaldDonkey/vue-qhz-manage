<template>
  <section class="overh">
    <div class="warp-operation warp-top overh">
      <el-button type="primary" size="medium" icon="el-icon-circle-plus-outline" @click="dialogVisible = true">新 增</el-button>
    </div>
    <el-col class="warp-container">
      <el-table ref="multipleTable" class="qhz-table" v-loading="listLoading" :data="tableData" tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="srId" label="ID" width="60">
        </el-table-column>
        <el-table-column prop="srName" label="角色名称" width="120"></el-table-column>
        <el-table-column prop="srShortName" label="角色简称"> </el-table-column>
        <el-table-column prop="tag" width="280" label="" filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="editor(scope.$index, scope.row)">编 辑</el-button>
            <el-button type="primary" size="mini">修改权限</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)">删 除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--<div class="block">-->
        <!--<el-pagination-->
          <!--@size-change="handleSizeChange"-->
          <!--@current-change="handleCurrentChange"-->
          <!--:current-page="currentPage"-->
          <!--:page-sizes="[10, 20, 30, 40]"-->
          <!--:page-size="100"-->
          <!--layout="total, sizes, prev, pager, next, jumper"-->
          <!--:total="total">-->
        <!--</el-pagination>-->
      <!--</div>-->
    </el-col>
    <el-dialog
      title="新增"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="closeDialog">
      <el-form ref="addForm" :model="form" label-width="80px">
        <el-form-item label="角色名称">
          <el-input v-model="form.srName"></el-input>
        </el-form-item>
        <el-form-item label="角色简称">
          <el-input v-model="form.srShortName"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="addSeave()" :loading="editLoading">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="编辑" :visible.sync="dialogFormVisible" :before-close="closeDialog" size="tiny">
      <el-form ref="editorForm" :model="form" label-width="80px">
        <el-form-item label="角色名称">
          <el-input v-model="form.srName"></el-input>
        </el-form-item>
        <el-form-item label="角色简称">
          <el-input v-model="form.srShortName"></el-input>
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
        dialogFormVisible: false,
        editLoading: false,
        form: {
          srId: '',
          srName: '',
          srShortName: ''
        },
        tableData: [
          {
            srId: '1',
            srName: '111',
            srShortName: '111'
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
        this.$fetch('/permissions/role', {page: 1, rows: 30})
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
      handleEdit (index, row) {
        this.dialogFormVisible = true
        this.form = Object.assign({}, row)
        this.table_index = index
      },
      cancel () {
        this.dialogVisible = false
        this.dialogFormVisible = false
        this.resetStu()
      },
      handleSave () {
        if (this.form.srName === '') {
          this.$message({
            message: '角色名称不能为空！',
            type: 'warning'
          })
        } else if (this.form.srShortName === '') {
          this.$message({
            message: '角色简称不能为空！',
            type: 'warning'
          })
        } else {
          this.$confirm('确认提交吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            cancelButtonClass: 'cancel'
          }).then(() => {
            this.editLoading = true
            this.$fetch('/permissions/role/update', {srId: this.tableData.srId, srName: this.form.srName, srShortName: this.form.srShortName})
              .then(res => {
                if (res.status === 200) {
                  this.rqList()
                  this.editLoading = false
                  this.dialogFormVisible = false
                  this.$message({
                    message: '操作成功！',
                    type: 'success'
                  })
                } else {
                  this.rqList()
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
      },
      handleDelete (index, row) {
        this.$confirm('确认删除', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$fetch('/permissions/role/delete', {srId: row.srId})
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
      },
      addSeave () {
        if (this.form.srName === '') {
          this.$message({
            message: '角色名称不能为空！',
            type: 'warning'
          })
        } else if (this.form.srShortName === '') {
          this.$message({
            message: '角色简称不能为空！',
            type: 'warning'
          })
        } else {
          this.editLoading = true
          this.$fetch('/permissions/role/save', {srName: this.form.srName, srShortName: this.form.srShortName})
            .then(res => {
              this.editLoading = false
              this.dialogFormVisible = false
              if (res.status === 200) {
                this.rqList()
                this.$message({
                  message: '角色添加成功！',
                  type: 'success'
                })
                this.editLoading = false
                this.dialogVisible = false
              } else {
                this.rqList()
                this.$message({
                  message: '角色添加失败！',
                  type: 'warning'
                })
              }
            })
          this.resetStu()
        }
      },
      resetStu: function () {
        this.form = {
          srId: '',
          srName: '',
          srShortName: ''
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

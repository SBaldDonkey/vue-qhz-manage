<template>
  <section class="overh">
    <div class="warp-operation warp-top">
      <el-button type="primary" size="medium" icon="el-icon-circle-plus-outline" @click="handleEdit()">新 增</el-button>
    </div>
    <el-col class="warp-container">
      <el-table ref="multipleTable" class="qhz-table" v-loading="listLoading" :data="tableData" tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="id" label="ID" width="60">
        </el-table-column>
        <el-table-column prop="username" label="员工用户名" width="120"> </el-table-column>
        <el-table-column prop="name" label="员工名称" width="100"> </el-table-column>
        <el-table-column prop="sdName" label="部门名称" width="100"> </el-table-column>
        <el-table-column prop="srName" label="角 色" width="100"> </el-table-column>
        <el-table-column prop="sjName" label="职位名称" width="100"> </el-table-column>
        <el-table-column prop="phone" label="手机号码" width="120"> </el-table-column>
        <el-table-column prop="tel" label="座机号码" width="120"> </el-table-column>
        <el-table-column prop="qq" label="Q Q" width="120"> </el-table-column>
        <el-table-column prop="email" label="邮 箱" width="220"> </el-table-column>
        <el-table-column prop="tag" label="操 作" filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="handleEdit(scope.$index, scope.row, scope.column)">修 改</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)">删 除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" :before-close="closeDialog" size="tiny">
      <el-form ref="editorForm" :model="form" label-width="90px">
        <el-form-item label="员工用户名">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="员工名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="部门名称">
          <el-select v-model="form.sdName" clearable placeholder="请选择">
            <el-option
              v-for="item in department"
              :key="item.sdId"
              :label="item.sdName"
              :value="item.sdId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="form.srName" clearable placeholder="请选择">
            <el-option
              v-for="item in role"
              :key="item.srId"
              :label="item.srName"
              :value="item.srId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="职位名称">
          <el-select v-model="form.sjName" clearable placeholder="请选择">
            <el-option
              v-for="item in job"
              :key="item.sjId"
              :label="item.sjName"
              :value="item.sjId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手机号码">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="座机号码">
          <el-input v-model="form.tel"></el-input>
        </el-form-item>
        <el-form-item label="QQ">
          <el-input v-model="form.qq"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addSeave(tab)" :loading="editLoading">{{dialogTitle}}</el-button>
          <el-button @click="cancel">取 消</el-button>
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
        editLoading: false,
        dialogFormVisible: false,
        listLoading: true,
        dialogTitle: '',
        tab: false,
        form: {
          id: '',
          username: '',
          name: '',
          sdName: '',
          srName: '',
          sjName: '',
          phone: '',
          tel: '',
          qq: '',
          email: ''
        },
        department: [],
        role: [],
        job: [],
        tableData: []
      }
    },
    mounted () {
      this.rqList()
    },
    methods: {
      rqList () {
        this.$fetch('/permissions/employees', {page: 1, rows: 30})
          .then((res) => {
            this.listLoading = false
            this.tableData = res.rows
          })
      },
      handleEdit (index, row, column) {
        this.$fetch('/permissions/department', {page: 1, rows: 30}) // 部门
          .then(res => {
            if (res) {
              this.department = res.rows
            }
          })
        this.$fetch('/permissions/role', {page: 1, rows: 30}) // 角色
          .then(res => {
            if (res) {
              this.role = res.rows
            }
          })
        this.$fetch('/permissions/position', {page: 1, rows: 30}) // 职位
          .then(res => {
            if (res) {
              this.job = res.rows
            }
          })
        if (index === undefined && row === undefined && column === undefined) {
          this.dialogVisible = true
          this.dialogTitle = '新 增'
          this.tab = true
        } else if (column.label === '操 作') {
          this.dialogTitle = '操 作'
          this.dialogVisible = true
          this.form = Object.assign({}, row)
          this.table_index = index
          this.tab = false
        }
      },
      cancel () {
        this.dialogVisible = false
        this.resetStu()
      },
      addSeave (tab) {
        let parameter = {
          id: this.form.id,
          username: this.form.username,
          name: this.form.name,
          departId: this.form.sdName,
          roleId: this.form.srName,
          jobId: this.form.sjName,
          phone: this.form.phone,
          tel: this.form.tel,
          qq: this.form.qq,
          email: this.form.email
        }
        if (tab) {
          this.$fetch('/permissions/employees/save', parameter)
            .then(res => {
              if (res.status === 200) {
                this.rqList()
                this.resetStu()
                this.$message({
                  message: '添加成功！',
                  type: 'success'
                })
                this.editLoading = false
                this.dialogVisible = false
              } else {
                this.$message({
                  message: res.msg,
                  type: 'warning'
                })
              }
            })
        } else {
          this.$confirm('确认提交吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            cancelButtonClass: 'cancel'
          }).then(() => {
            console.log(parameter)
            this.editLoading = true
            this.$fetch('/permissions/employees/update', parameter)
              .then((res) => {
                if (res.status === 200) {
                  this.tableData.splice(this.table_index, 1, this.form)
                  this.resetStu()
                  this.editLoading = false
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
            this.editLoading = false
            this.dialogVisible = false
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
          this.$fetch('/permissions/employees/delete', {id: row.id})
          this.tableData.splice(index, 1)
          this.$message({
            message: '操作成功！',
            type: 'success'
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      resetStu: function () {
        this.form = {
          username: '',
          name: '',
          sdName: '',
          srName: '',
          sjName: '',
          sjId: '',
          srId: '',
          sdId: '',
          phone: '',
          tel: '',
          qq: '',
          email: ''
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

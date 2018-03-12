<template>
  <section class="overh">
    <div class="warp-operation warp-top">
      <el-button type="primary" size="medium" icon="el-icon-circle-plus-outline" @click="dialogVisible = true">新 增</el-button>
    </div>
    <el-col class="warp-container">
      <el-table ref="multipleTable" class="q vhz-table" v-loading="listLoading" :data="tableData" tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="id" label="ID" width="60"></el-table-column>
        <el-table-column prop="number" label="序号" width="60"></el-table-column>
        <el-table-column prop="image" label="图片" width="80">
          <template slot-scope="scope">
            <img :src="scope.row.image" alt="" style="width: 50px;height: 50px">
          </template>
        </el-table-column>
        <el-table-column prop="link" label="跳转链接" width="200"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="190"></el-table-column>
        <el-table-column prop="upTime" label="更新时间" width="190"></el-table-column>
        <el-table-column prop="tag" label="操 作" filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="editor(scope.$index, scope.row)">编 辑</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)" :loading="editLoading">删 除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
    <el-dialog title="新 增" :visible.sync="dialogVisible" size="tiny" :before-close="closeDialog">
      <el-form ref="editorForm" :model="form" label-width="80px">
        <el-form-item label="序号">
          <el-input v-model="form.number"></el-input>
        </el-form-item>
        <el-form-item label="跳转链接">
          <el-input v-model="form.link"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addSeave()" :loading="editLoading">新 增</el-button>
          <el-button @click="cancel">取 消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="编 辑" :visible.sync="dialogFormVisible" size="tiny" :before-close="closeDialog">
      <el-form ref="editorForm" :model="form" label-width="80px">
        <el-form-item label="序号">
          <el-input v-model="form.number"></el-input>
        </el-form-item>
        <el-form-item label="图片">
          <el-input v-model="form.image"></el-input>
        </el-form-item>
        <el-form-item label="跳转链接">
          <el-input v-model="form.link"></el-input>
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
        tableData: [
          {
            id: 1,
            number: '1',
            image: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517825800461&di=2a3c1842782cfff8981914785a6008f5&imgtype=0&src=http%3A%2F%2Fa1.att.hudong.com%2F85%2F53%2F20300001385827139624534567673.jpg',
            link: 'http://www.baidu.com',
            createTime: '2015-02-03 13:13:13',
            upTime: '2015-02-03 13:13:13'
          }
        ],
        form: {
          id: '',
          number: '',
          image: '',
          link: '',
          createTime: '',
          upTime: ''
        }
      }
    },
    mounted () {
      this.resetStu()
    },
    methods: {
      addSeave () {
        if (this.form.number === '') {
          this.$message({
            message: '序号不能为空！',
            type: 'warning'
          })
        } else {
          this.editLoading = true
          this.$fetch('', {sjName: this.form.sjName, sjShortName: this.form.sjShortName})
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
      cancel () {
        this.dialogVisible = false
        this.dialogFormVisible = false
        this.resetStu()
      },
      editor (index, row) { // 编辑按钮事件
        this.dialogFormVisible = true
        this.form = Object.assign({}, row)
        this.table_index = index
      },
      handleSave () { // 修改
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
      },
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

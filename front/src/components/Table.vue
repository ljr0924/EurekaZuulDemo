<template>
  <div id="table">
    <!-- start 表单筛选区域，功能按钮区域 -->
    <el-row :gutter="20">
      <el-col :span="24">
        <el-form :inline="true" :model="filterForm" ref="filter">
          <el-form-item label="组件名称">
            <el-input v-model="filterForm.name" placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item label="分类">
            <el-input v-model="filterForm.category" placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <!-- <el-input v-model="filterForm.status" placeholder="请输入"></el-input> -->
            <el-select v-model="filterForm.status" placeholder="请选择" clearable>
              <el-option label="上架" value="1" />
              <el-option label="下架" value="0" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="fetchAllComponent()">查询</el-button>
            <el-button @click="resetForm()">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="24">
        <el-button type="primary" @click="openComponentForm()">新增</el-button>
        <el-button type="danger" @click="deleteComponents(multipleSelection)">删除</el-button>
        <el-button @click="changeStatus(1)">上架</el-button>
        <el-button @click="changeStatus(0)">下架</el-button>
      </el-col>
    </el-row>
    <!-- end 表单筛选区域，功能按钮区域 -->

    <!-- start 表格区域 -->
    <el-table
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="name" label="组件名称"></el-table-column>
      <el-table-column prop="category" label="分类"></el-table-column>
      <el-table-column prop="version" label="版本"></el-table-column>
      <el-table-column prop="status" label="状态" width="120">
        <template slot-scope="scope">
          <div v-if="scope.row.status == 0">下架</div>
          <div v-else>上架</div>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="openComponentForm(scope.row, title='编辑组件')">编辑</el-button>
          <el-button size="mini" type="danger" @click="deleteComponents([scope.row])">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- end 表格区域 -->

    <!-- start 分页区域 -->
    <div style="margin-top: 5px;">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 30, 50, 100]"
        :page-size="pageSize"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
      ></el-pagination>
    </div>
    <!-- end 分页区域 -->

    <!-- start 组件表单区域 -->
    <el-dialog :title="formTitle" :visible.sync="componentFormVisible" width="500px">
      <el-form
        label-position="left"
        :model="componentForm"
        :label-width="formLabelWidth"
        :rules="rules"
        ref="componentForm"
      >
        <el-form-item label="组件名称" prop="name">
          <el-input v-model="componentForm.name" autocomplete="off" required />
        </el-form-item>
        <el-form-item label="分类" prop="category">
          <el-input v-model="componentForm.category" autocomplete="off" required />
        </el-form-item>
        <el-form-item label="版本" prop="version">
          <el-input v-model="componentForm.version" autocomplete="off" required />
        </el-form-item>
        <el-form-item label="状态">
          <el-switch
            v-model="componentForm.status"
            active-text="上架"
            inactive-text="下架"
            :active-value="1"
            :inactive-value="0"
          ></el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="componentFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitComponentForm()">确 定</el-button>
      </div>
    </el-dialog>
    <!--  end 组件表单区域 -->
  </div>
</template>

<script type="text/ecmascript-6">
import componentApi from "../api/component";

export default {
  data() {
    return {
      tableData: [],
      multipleSelection: [],

      // 筛选表单
      filterForm: {
        name: "",
        category: "",
        status: ""
      },
      // 组件表单
      formLabelWidth: "80px",
      formTitle: "",
      componentFormVisible: false,
      componentForm: {},
      rules: {
        name: [{ required: true, message: "请输入组件名称", trigger: "blur" }],
        category: [
          { required: true, message: "请输入组件分类", trigger: "blur" }
        ],
        version: [
          { required: true, message: "请输入组件版本", trigger: "blur" }
        ]
      },
      currentUpdate: [],
      // 分页
      currentPage: 1,
      pageSize: 10,
      total: null
    };
  },

  created() {
    this.fetchAllComponent(1, 10);
  },

  methods: {
    // 初始化组件表单
    initComponentForm() {
      return { name: "", category: "", version: "", status: "0" };
    },
    // 查询组件
    fetchAllComponent() {
      // eslint-disable-next-line no-console
      componentApi
        .fetchAllComponent(this.currentPage, this.pageSize, this.filterForm)
        .then(res => {
          // eslint-disable-next-line no-console
          console.log(res);
          // eslint-disable-next-line no-console
          if (res.status === 200) {
            this.tableData = res.data.list;
            this.currentPage = res.data.pageNum;
            this.pageSize = res.data.pageSize;
            this.total = res.data.total;
          }
        })
        .catch(err => {
          // eslint-disable-next-line no-console
          console.log("error");
          // eslint-disable-next-line no-console
          console.log(err);
        });
    },

    // 打开组件表单
    openComponentForm(row, title = "新增组件") {
      this.componentForm = row
        ? JSON.parse(JSON.stringify(row))
        : this.initComponentForm();
      this.formTitle = title;
      this.componentFormVisible = true;
    },
    // 表格选择响应函数
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

    // 重置筛选表单
    resetForm() {
      // eslint-disable-next-line no-console
      this.filterForm = {
        name: "",
        category: "",
        status: ""
      };
    },
    // 每页数量变化响应函数
    handleSizeChange(val) {
      // eslint-disable-next-line no-console
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.fetchAllComponent();
    },
    // 页码发生变化
    handleCurrentChange(val) {
      // eslint-disable-next-line no-console
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.fetchAllComponent();
    },

    // 上架或者下架
    changeStatus(status) {
      if (this.multipleSelection.length === 0) return;
      const temp = JSON.parse(JSON.stringify(this.multipleSelection));
      let data = temp.map(val => {
        val.status = status;
        this.currentUpdate.push(val.id);
        return val;
      });
      // eslint-disable-next-line no-console
      console.log(data);

      componentApi.updateComponentStatus(data).then(res => {
        // eslint-disable-next-line no-console
        console.log(res);
        this.remind(res);
        if (res.data.code === 200) {
          this.tableData = this.tableData.map(val => {
            if (this.currentUpdate.indexOf(val.id) > -1) {
              val.status = status;
            }
            // eslint-disable-next-line no-console
            return val;
          });
        }
      });
    },

    // 删除组件
    deleteComponents(componentList) {
      if (componentList.length === 0) return;
      const data = componentList.map(val => {
        return val.id;
      });

      this.$confirm("是否删除该组件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        componentApi.deleteComponents(data).then(res => {
          // eslint-disable-next-line no-console
          console.log(res);
          if (res.data.code === 200) {
            this.tableData = this.tableData.filter(val => {
              return data.indexOf(val.id) === -1;
            });
            this.total = this.total - componentList.length;
          }
          this.remind(res);
        });
      });

      // eslint-disable-next-line no-console
      console.log(data);
    },

    // 提交组件表单
    submitComponentForm() {
      // 验证表单
      this.$refs["componentForm"].validate(valid => {
        if (!valid) return false;
        // 向后端发送数据
        if (this.formTitle === "新增组件") this.addComponent();
        else this.updateComponent();
      });
    },

    // 后端响应提示
    remind(res) {
      this.$message({
        showClose: true,
        message: res.data.msg,
        type: res.data.code === 200 ? "success" : "error"
      });
    },

    // 新增组件
    addComponent() {
      componentApi
        .saveComponent(this.componentForm)
        .then(res => {
          // eslint-disable-next-line no-console
          console.log(res);
          this.remind(res);
          if (res.data.code === 200) {
            this.tableData.push(this.componentForm);
            this.total++;
            this.componentForm = this.initComponentForm();
          }
        })
        .catch(err => {
          // eslint-disable-next-line no-console
          console.log(err);
        });
    },

    // 更新组件
    updateComponent() {
      componentApi.updateComponent(this.componentForm).then(res => {
        // eslint-disable-next-line no-console
        console.log(res);

        this.remind(res);
        if (res.data.code === 200) {
          this.tableData = this.tableData.map(val => {
            if (this.componentForm.id === val.id) {
              val = JSON.parse(JSON.stringify(this.componentForm));
            }
            return val;
          });
        }
      });
    }
  }
};
</script>

<style scoped lang="stylus" rel="stylesheet/stylus"></style>

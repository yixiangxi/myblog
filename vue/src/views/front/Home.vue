<template>
  <div class="main-content">
    <div style="display: flex;align-items: flex-start;grid-gap: 20px;margin-top: 20px">
      <!--      align-items: flex-start;这将使得容器内的元素在交叉轴上（默认是垂直方向）与容器的起始位置对齐-->
      <div style="width: 150px" class="card">

        <div class="category-item" v-for="item in categoryList" :key="item.id"
             :class="{'category-item-active':item.name=== current} " @click="selectCategory(item.name)">
          {{ item.name }}
        </div>

      </div>
      <div style="flex: 1;" class="card">
        <div class="blog-box" v-for="item in tableData" :key="item.id" v-if="total>0">
          <div style="flex: 1;width: 0;padding-bottom: 12px;">
            <div style="font-size: 16px;font-weight: bold;margin-bottom: 10px">{{ item.title }}</div>
            <div class="overflow-hidden" style="margin-bottom: 10px;color: #8a919f;font-size: 12px">{{ item.descr }}
            </div>
            <div style="display: flex ">
              <div style="flex: 1;  color: #8a919f;font-size: 12px">
                <span style="margin-right: 20px"><i class="el-icon-user"></i>{{ item.userName }}</span>
                <span style="margin-right: 20px"><i class="el-icon-like"></i>{{ item.likesCount }}</span>
                <span style="margin-right: 20px"><i class="el-icon-eye"></i>{{ item.readCount }}</span>
              </div>

              <div style="width: fit-content">
                <el-tag type="info" style="margin-right: 5px">{{ item.categoryName }}</el-tag>

              </div>
            </div>

          </div>
          <div style="width:109.6px;margin-left: 18px">
            <img style="width: 100%;border-radius: 3px;height: 73.6px" :src=item.cover>
          </div>
        </div>
        <div v-if="total === 0" class="blog-empty">
          <img src="@/assets/imgs/box.png" style="width: 20px;height: auto;vertical-align: middle;">
          暂无数据
        </div>

        <div style="margin-top: 20px" v-if="total>0">
          <el-pagination
              background
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[5, 10, 20]"
              :page-size="pageSize"
              layout="total, prev, pager, next"
              :total="total">
          </el-pagination>
        </div>


      </div>
      <div style="width: 260px">
        <div class="card" style="margin-bottom: 20px">
          <a href="/front/person" style="color: inherit;text-decoration: none; ">
            <div style="font-size: 16px;font-weight: bold;margin-bottom: 10px">{{ greeting }}</div>
            <div style="color: #8a919f;font-size: 14px">写下博客记录美好一天</div>
          </a>

        </div>
        <div class="card">
          <div style="display: flex;align-items: center;border-bottom: 1px solid #ddd;padding-bottom: 10px">
            <div style="flex: 1;font-size:large;"><i class="el-icon-hot"></i>文章榜</div>
            <div style="color: #8a919f ;font-size: small;cursor: pointer" @click="refreshTop"><i class="el-icon-refresh"></i>换一换</div>
          </div>

          <div style="margin-top: 5px">
            <div v-for="item in showTopList" :key="item.id" class="overflow-hidden" style="padding: 8px">
              <span style="width: 20px;display: inline-block;margin-right: 10px">
<!--                在排序外添加一个span标签 添加宽度以及 display: inline-block ，将行内元素设置为内联块允许设置宽、高-->
              <span v-if="item.index === 1" style="color: orangered;width: 20px;margin-right: 11px;font-weight: bold">{{item.index}}</span>
              <span v-else-if="item.index === 2" style="color: goldenrod;margin-right: 11px;font-weight: bold">{{item.index}}</span>
              <span v-else-if="item.index === 3" style="color: deepskyblue;margin-right: 11px;font-weight: bold">{{item.index}}</span>
             <span v-else style="color: #ddd;margin-right: 11px;font-weight: bold">{{item.index}}</span>
              </span>

              <span style="">{{item.title}}</span>

            </div>

          </div>
        </div>

      </div>
    </div>

  </div>
</template>

<script>

export default {

  data() {
    return {
      categoryList: [],
      current: "全部博客",
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 5,  // 每页显示的个数
      userName: null,
      categoryName: null,
      title: null,
      total: 0,
      currentHours: new Date().getHours(),
      blogTopList:[],//后端查询数据暂存于该数组
      showTopList:[],//前端用于渲染展示的数组
      lastIndex:0,
    }
  },
  mounted() {
    this.load();
    this.loadBlog(1);
    this.refreshTop();

  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    load() {
      this.$request.get("/category/selectAll").then(res => {
        this.categoryList = res.data || []
        this.categoryList.unshift({name: "全部博客"})
      })
    },
    selectCategory(categoryName) {
      this.current = categoryName;

      this.loadBlog(1)
    },
    loadBlog(pageNum) {
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/blog/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          // 传入查询的博客类别到后端，如果前端选择为全部博客则传入为空
          categoryName: this.current === "全部博客" ? null : this.current,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })

    },
    // 分页
    handleCurrentChange(pageNum) {
      this.loadBlog(pageNum)
    },
    // 点击刷新榜单
    refreshTop(){
      this.$request.get("/blog/selectTop").then(res => {
        this.blogTopList = res.data || []
        let i = 1
        this.blogTopList.forEach(item=>item.index = i++)


        if(this.lastIndex === 20 ){
          this.lastIndex = 0;
        }

        this.showTopList =this.blogTopList.slice(this.lastIndex,this.lastIndex+5)

        this.lastIndex+=5;

      })

    }

  },
  computed: {
    greeting() {
      if (this.currentHours >= 5 && this.currentHours < 12) {
        return "早上好！"
      } else if (this.currentHours >= 12 && this.currentHours < 18) {
        return "下午好！"
      } else
        return "晚上好！"
    }
  }
}
</script>

<style>
.category-item {
  font-size: 16px;
  padding: 10px 17px;
  text-align: center;
  cursor: pointer;

}

.category-item-active {
  background-color: #f96332;
  color: #fff;

  border-radius: 5px;
}

.overflow-hidden {
  white-space: nowrap; /* 防止文字换行 */
  overflow: hidden; /* 隐藏溢出部分 */
  text-overflow: ellipsis; /* 当溢出时显示省略号 */

}

.blog-box {
  display: flex;
  border-bottom: 1px solid #ddd;
  margin-bottom: 10px;
}


.blog-empty {
  padding: 60px;
  font-size: 20px;
  color: #8a919f;
  text-align: center;
  justify-content: center;
  align-items: center;
}
</style>

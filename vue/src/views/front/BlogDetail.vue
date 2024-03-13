<template>
  <!--  最外层主体内容占比-->
  <div class="main-content">
    <div style="display: flex;align-items: flex-start;grid-gap: 20px;margin-top: 20px">
      <!--    博客详情页面左侧-->
      <div class="card" style="flex: 1;padding: 32px">
        <div class="blog-detail-title">{{ blog.title }}</div>
        <div style="color: #8a919f;margin-top: 10px">
          <span style="margin-right: 20px"><i class="el-icon-user"></i>{{blog.userName}}</span>
          <span style="margin-right: 20px"><i class="el-icon-date"></i>{{blog.date}}</span>
          <span style="margin-right: 20px"><i class="el-icon-eye"></i>{{blog.readCount}}</span>
          <span v-for="item in JSON.parse(blog.tags||'[]')" :key="item.id">
            <el-tag style="margin-right: 5px">{{item}}</el-tag>
          </span>
        </div>

        <div class="w-e-text" style="margin-top: 10px">
          <div v-html="blog.content"></div>

        </div>

      </div>
      <!--    博客详情页面右侧-->
      <div class="card" style="width: 240px"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BlogDetail",
  data() {
    return {
      blogId: this.$route.query.blogId,
      blog: {},
    }

  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.$request.get("/blog/selectById/" + this.blogId).then(res => {
        this.blog = res.data || {}
      })
    }
  }
}
</script>

<style>
.blog-detail-title {
  font-size: 20px;
  font-weight: bold
}

/* blockquote 样式 */
blockquote {
  display: block;
  border-left: 8px solid #d0e5f2;
  padding: 20px 10px;
  margin: 10px 0;
  line-height: 1.4;
  font-size: 100%;
  background-color: #f1f1f1;
}

/* code 样式 */
code {
  display: inline-block;
  *display: inline;
  *zoom: 1;
  background-color: #f1f1f1;
  border-radius: 3px;
  padding: 3px 5px;
  margin:  3px;
}
pre code {
  display: block;
}
p {
  line-height: 30px
}
</style>
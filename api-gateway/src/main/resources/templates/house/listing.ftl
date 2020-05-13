<!DOCTYPE html>

<html lang="en-US">
<@common.header/>

<body class="page-sub-page page-listing-lines page-search-results" id="page-top">
<div class="wrapper">
   <@common.nav/>
    <div id="page-content">
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="#">Home</a></li>
                <li class="active">房产列表</li>
            </ol>
        </div>

        <div class="container">
            <div class="row">
                <!-- Results -->
                <div class="col-md-9 col-sm-9">
                    <section id="results">
                        <header><h1>房产列表</h1></header>
                        <section id="search-filter">
                            <figure><h3><i class="fa fa-search"></i>搜索结果:</h3>
                                <span class="search-count"></span>
                                 <div class="sorting">
                                    <div class="form-group">
                                        <select name="sorting" id="sorting">
                                        <option value="">排序</option>
                                        <option value="price_asc"   <#if (vo.sort) == "price_asc">   selected </#if>  >价格由低到高</option>
                                        <option value="price_desc"  <#if (vo.sort) == "price_desc">  selected </#if> >价格由高到低</option>
                                            <option value="time_desc"   <#if (vo.sort) == "time_desc">   selected </#if> >加入时间</option>
                                        </select>
                                    </div>
                                </div>
                            </figure>
                        </section>
                    <section id="properties" class="display-lines">
                      <#list ps.list as house> 
                       
                            <div class="property">
                                <figure class="tag status">${house.typeStr}</figure>
                                <div class="property-image">
                                    <figure class="ribbon">In Hold</figure>
                                    <a href="/house/detail?id=${house.id}">
                                        <img alt="" src="${house.firstImg}" style="width: 260px;height: 195px" >
                                    </a>
                                </div>

                                <div class="info">
                                    <header>
                                        <a href="/house/detail?id=${house.id}"><h3>${house.name}</h3></a>
                                        <figure>${house.address}</figure>

                                    </header>
                                    <div class="tag price">￥ ${house.price}万</div>
                                    <aside>
                                         <p>${house.remarks}
                                        </p>

                                        <dl>
                                            <dt>Status:</dt>
                                                <dd>Sale</dd>
                                            <dt>面积:</dt>
                                                <dd>${house.area} m<sup>2</sup></dd>
                                            <dt>卧室:</dt>
                                                <dd>${house.beds}</dd>
                                            <dt>浴室:</dt>
                                                <dd>${house.baths}</dd>
                                        </dl>
                                    </aside>
                                    <a href="/house/detail?id=${house.id}" class="link-arrow">更多信息</a>
                                </div>
                            </div>
                        </#list>
                       </section>
                            <!--分页 -->
                            <div class="center">
                                 <@common.paging ps.pagination/>
                            </div>
                        </section>
                    </section>
                </div>

                <!-- 搜索栏 -->
                <div class="col-md-3 col-sm-3">
                    <section id="sidebar">
                        <@common.search />
                        <@common.hot />
                        
                    </section>
                </div>
            </div>
        </div>
    </div>
     <@common.footer/>
</div>

<@common.js/>
<script type="text/javascript" src="assets/js/ie.js"></script>
 <script  type="text/javascript" >
     

     $(document).ready(function() {
          var errorMsg   = "${errorMsg!""}";
          var successMsg = "${successMsg!""}";
          if(errorMsg){ 
              errormsg("error",errorMsg);
          }
          if(successMsg) {
              successmsg("success",successMsg);
          }
        })
      
      
  
     
      $('#sorting').change(function() {
           var type =  $(this).val();
           if (!type) {
               return;
           }
           window.location.href=  "/house/list?sort="+type+"&name=" + "${(vo.name)!}" + "&type=" + "${(vo.type)!0}" ;
       });

 </script>

</body>
</html>
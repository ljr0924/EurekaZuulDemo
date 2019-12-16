import axios from 'axios';

const urlPrefix = 'http://localhost:8010'


// 获取组件信息
function fetchAllComponent(currentPage, pageSize, filterForm) {
  return axios.get(urlPrefix + '/select/components/', {
    params:{
      page: currentPage,
      limit: pageSize,
      ...filterForm
    }
  })
}

// 获取一个组件信息
function fetchComponent(id) {
  return axios.get(urlPrefix + `/select/components/${id}`)
}

// 删除组件信息
function deleteComponents(componentNameList) {
  // eslint-disable-next-line no-console
  console.log(JSON.stringify(componentNameList))
  return axios.delete(urlPrefix + '/delete/components/', {
    data: componentNameList
  })
}

// 批量上架或者下架
function updateComponentStatus(componentList) {
  return axios.put(urlPrefix + '/update/components/status', componentList)
}

// 更新单个组件信息
function updateComponent(data) {
  return axios.put(urlPrefix + '/update/components/', data)
}

// 新增一个组件
function saveComponent(componentForm) {
  return axios.post(urlPrefix + '/add/components/', componentForm)
}

export default {
  fetchAllComponent,
  fetchComponent,
  deleteComponents,
  updateComponentStatus,
  updateComponent,
  saveComponent
}
from bs4 import BeautifulSoup
import requests
from urllib.request import urlopen
import json
from time import sleep
from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import ElementNotVisibleException
from selenium.webdriver.common.keys import Keys
from selenium.common.exceptions import StaleElementReferenceException



options = webdriver.ChromeOptions()
options.add_argument('headless')
options.add_argument('lang=ko_KR')

driver = webdriver.Chrome('./chromedriver', options=options)

def main():
    global driver, load_wb, review_num, dict_store, dict_store_one

    driver.implicitly_wait(3)  # 렌더링 될때까지 기다린다 4초
    driver.get('https://map.kakao.com/')  # 주소 가져오기

    with open('si.json', 'r', encoding='utf-8-sig') as f:
        data = json.load(f)
        # jtopy = json.dumps(data)
        data2 = data['store']

    dict_store = {}
    dict_store_one = {}
    for i in range(6931, 17404):
        print(i)
        # print(allComments)

        if i % 5 == 0 and i != 0:
            sleep(0.5)
        if i % 31 == 0 and i != 0:
            sleep(1)
        if i % 61 == 0 and i != 0:
            sleep(1)
        query = data2[i]['상호명'] + " " + data2[i]['도로명주소'].split(' ')[2]

        store_name = data2[i]['상호명']
        print(store_name)

        search(query, i, store_name)
        dict_store['store'] = dict_store_one
        if i % 10 == 0:
            with open('si_crawling.json', 'a', encoding='UTF-8-sig') as file:
                file.write(json.dumps(dict_store, ensure_ascii=False))
                print('****************저장완료****************')
    driver.quit()
    print("finish")

def search(place,indexs, store_name):
    global driver

    search_area = driver.find_element_by_xpath('//*[@id="search.keyword.query"]')  # 검색 창
    search_area.send_keys(place)  # 검색어 입력
    driver.find_element_by_xpath('//*[@id="search.keyword.submit"]').send_keys(Keys.ENTER)  # Enter로 검색
    sleep(1)

    # 검색된 정보가 있는 경우에만 탐색
    # 1번 페이지 place list 읽기
    html = driver.page_source

    soup = BeautifulSoup(html, 'html.parser')
    place_lists = soup.select('.placelist > .PlaceItem')  # 검색된 장소 목록

    # 검색된 첫 페이지 장소 목록 크롤링하기
    crawling(place, place_lists, indexs, store_name)
    search_area.clear()


def crawling(place, place_lists, indexs, store_name):
    """
    페이지 목록을 받아서 크롤링 하는 함수
    :param place: 리뷰 정보 찾을 장소이름
    """

    while_flag = False
    for i, place in enumerate(place_lists):
        # 광고에 따라서 index 조정해야함
        # if i >= 3:
        #   i += 1

        place_name = place.select('.head_item > .tit_name > .link_name')[0].text  # place name
        place_address = place.select('.info_item > .addr > p')[0].text  # place address

        detail_page_xpath = '//*[@id="info.search.place.list"]/li[' + str(1) + ']/div[5]/div[4]/a[1]'
        driver.find_element_by_xpath(detail_page_xpath).send_keys(Keys.ENTER)
        driver.switch_to.window(driver.window_handles[-1])  # 상세정보 탭으로 변환
        sleep(1)


        # 첫 페이지
        extract_review(place_name, indexs, store_name)

        driver.close()
        driver.switch_to.window(driver.window_handles[0])  # 검색 탭으로 전환


def extract_review(place_name, indexs, store_name):
    global driver, dict_store, dict_store_one
    ret = True
    blog_text = []
    rank_text=[]
    html = driver.page_source
    soup = BeautifulSoup(html, 'html.parser')

    # 첫 페이지 리뷰 목록 찾기
    review_lists = soup.select('.list_evaluation > li')

    # 리뷰가 있는 경우
    if len(review_lists) != 0:
        if len(review_lists) != 0:
            for i, review in enumerate(review_lists):
                comment = review.select('.txt_comment > span')  # 리뷰
                rating = review.select('.grade_star > em')  # 별점
                val = ''
                grade=''
                if len(comment) != 0:
                    if len(rating) != 0:
                        val = comment[0].text
                        grade= rating[0].text
                        if(i==0):
                            dict_store_one['상호명'] = store_name
                            dict_store_one['리뷰'] = val
                            dict_store_one['점수'] = grade

                    else:
                        val = comment[0].text + ' /0'
                    print(val)
                    print(grade)

        else:
            print('no review in extract')
            ret = False

    return ret


if __name__ == "__main__":
    main()

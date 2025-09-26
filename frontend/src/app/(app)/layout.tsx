"use client"

import { SidebarProvider } from "@/components/ui/sidebar"
import { AppSidebar } from "@/components/layout/app-sidebar/app-sidebar"
import { useIsMobile } from "@/hooks/use-mobile"
import { MobileHeader } from "@/components/layout/header/mobile-header"
import { DesktopHeader } from "@/components/layout/header/desktop-header"
import { Navbar } from "@/components/layout/navbar/navbar"
import { useQuery } from "@tanstack/react-query"
import { getMe } from "@/api/auth-api"
import { redirect, RedirectType } from "next/navigation"

export default function AppLayout({ children }: { children: React.ReactNode }) {
  const isMobile = useIsMobile()

  const { data, error, isError, isLoading } = useQuery({
    queryKey: ["user"],
    queryFn: getMe,
  })

  if (isLoading) {
    return <div>Загрузка главной страницы....</div>
  }

  if (isError) {
    redirect("/login", RedirectType.push)
  }

  console.log(data)

  return (
    <SidebarProvider defaultOpen={false}>
      <AppSidebar />
      <main className="mx-auto container">
        {isMobile ? <MobileHeader /> : <DesktopHeader />}
        <div className={`${isMobile && "p-2"}`}>{children}</div>
        {isMobile && <Navbar />}
      </main>
    </SidebarProvider>
  )
}
